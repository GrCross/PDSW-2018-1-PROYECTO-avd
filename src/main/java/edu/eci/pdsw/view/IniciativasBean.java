/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.Estado;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author estudiante
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "iniciativasBean")
@SessionScoped
public class IniciativasBean extends BasePageBean {
    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa;
    
    private Iniciativa iniciativa;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Iniciativa> iniciativasParecidas;
    private Usuario autor;
    private ArrayList<String> palabrasClave;

    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public void procesarPalabrasClave(){
        String[] tempClaves = iniciativa.getPalabrasClave().split(",|\\{|\\}");
        palabrasClave =new ArrayList( Arrays.asList(tempClaves));
        palabrasClave.remove(0);
        for(String s: palabrasClave){
            s = s.replace("\"", "");
            s = s.replace("\"", "");
        }
    }
    
    public int likesUnaIniciativa (){
        try {
            return serviciosBancoIniciativa.LikesUnaIniciativa(iniciativa.getId()).getVotos();
        } catch (Exception ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
    

    public void redirect(String pagina){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina+".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarComentario(String contenido,Usuario usuario,int idIniciativa) {
        Date fechaDeCreacion = java.util.Calendar.getInstance().getTime();
        long id=0;          
        try {
            id = serviciosBancoIniciativa.consultarMax();
        } catch (Exception ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        Comentario comentario = new Comentario(usuario, fechaDeCreacion, contenido, id);
        
        
        try {
            serviciosBancoIniciativa.InsertarComentario(comentario,idIniciativa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Has añadido un comentario."));
        } catch (Exception ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void consultarIniciativasParecidas(){
        iniciativasParecidas = new ArrayList<>();
        ArrayList<Iniciativa> iniciativas = serviciosBancoIniciativa.consultarIniciativas();
        String[] palClavesAComparar = iniciativa.getPalabrasClave().split("\\W+");
        for(Iniciativa ini: iniciativas){
            
            String[] palClavesOtras = ini.getPalabrasClave().split("\\W+");
            List<String> pal1 = Arrays.asList(palClavesAComparar);
            List<String> pal2 = Arrays.asList(palClavesOtras);
            
            
            boolean seguir=true;
            for (int i = 1; i < pal1.size() && seguir; i++) {
                
                for (int j = 1; j < pal2.size(); j++) {
                    
                    if(levenshteinDistance(pal1.get(i), pal2.get(j)) < 3 && !(ini.getId()==iniciativa.getId())){
                        
                        iniciativasParecidas.add(ini);
                        
                        seguir=false;
                        
                        break;
                    }
                }
            }
        }
    }
    
    
    private int levenshteinDistance(String x, String y){
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                    
                } else if (j == 0) {
                    dp[i][j] = i;
                    
                } else {
                    int e = dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1));
                    int temp = Math.min(e, dp[i - 1][j] + 1);
                    int temp2 = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                    dp[i][j] = Math.min(temp, temp2);
                    
                }
            }
        }
         for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                
            }
            
        }
        return dp[x.length()][y.length()];
    }
    
    private int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
 
    
    
    public ArrayList<Comentario> consultarComentarios() throws Exception{
        comentarios = serviciosBancoIniciativa.consultarComentarios(iniciativa.getId());
        return serviciosBancoIniciativa.consultarComentarios(iniciativa.getId());
    }
    
    public boolean iniciativaHabilitada() {
    	if(iniciativa.getEstado().equals(Estado.En_Espera)) {
    		return true;
    	}
    	return false;
    }
    
    
    
    public boolean esCreador(Usuario user) {
    	autor=user;
    	
    	/*ELContext elContext = FacesContext.getCurrentInstance().getELContext();
    	LoginBean loginBean 
    	    = (LoginBean) FacesContext.getCurrentInstance().getApplication()
    	    .getELResolver().getValue(elContext, null, "LoginBean");*/
    	
    	if( iniciativa.getAutor().getCorreo().equals(autor.getCorreo()) || autor.getRol().equals(Rol.ADMINISTRADOR)) {
    		if(iniciativaHabilitada()){
                    return true;
                }
    	}
    	return false;
    }
    
    
    
    
    
    public void modificarDescripcion(String descripcion) {
    	
    	if(descripcion==null) {
    		descripcion=iniciativa.getDescripcion();
    	}
    	
    	try {
                serviciosBancoIniciativa.cambiarDescripcionIniciativa(descripcion,iniciativa.getId());
    		setIniciativa(serviciosBancoIniciativa.consultarIniciativa(iniciativa.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void modificarNombre(String nombre) {
    	if(nombre==null) {
    		nombre=iniciativa.getNombre();
    	}
    	try {
                serviciosBancoIniciativa.cambiarNombreIniciativa(nombre,iniciativa.getId());
    		setIniciativa(serviciosBancoIniciativa.consultarIniciativa(iniciativa.getId()));
    		
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    }

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

    
    public Iniciativa getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Iniciativa iniciativa) {
        this.iniciativa = iniciativa;
    }
    
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    
    public ArrayList<Iniciativa> getIniciativasParecidas() {
        return iniciativasParecidas;
    }

    public void setIniciativasParecidas(ArrayList<Iniciativa> iniciativasParecidas) {
        this.iniciativasParecidas = iniciativasParecidas;
    }
    
    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        CellStyle style = wb.createCellStyle();
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
 
        for (Row row : sheet) {        	
            for (Cell cell : row) {
            	
            	
                cell.setCellValue(cell.getStringCellValue().toUpperCase());
                cell.setCellStyle(style);
            }
        }
    }
    
    
    
    
}
