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
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Math.*;
import java.util.Arrays;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

    public ArrayList<Iniciativa> getIniciativasParecidas() {
        return iniciativasParecidas;
    }

    public void setIniciativasParecidas(ArrayList<Iniciativa> iniciativasParecidas) {
        this.iniciativasParecidas = iniciativasParecidas;
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
        iniciativasParecidas = new ArrayList<Iniciativa>();
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

    
    

}
