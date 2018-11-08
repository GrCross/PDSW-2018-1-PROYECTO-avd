package edu.eci.pdsw.view;
import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.view.BasePageBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.PersistenceException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rosal
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "insertIniciativasBean")
@SessionScoped
public class IniciativasUsuariosBean extends BasePageBean {
    //<h:graphicImage library="images" name="cabecera.jpg" style =" text-align : center;"> </h:graphicImage> 
	// no borrar lo de arriba puede ser util en el xhtml
    private int documento;
    private ArrayList<String> palabrasClave=new ArrayList<String>();
    private ArrayList<String> palabrasClaveConsultar=new ArrayList<String>();
    private ArrayList<Iniciativa> iniciativasClave=new ArrayList<Iniciativa>();

    
    
    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa; 
    
    public void registrarIniciativa (String nombre, String descripcion,String area,long documento) throws Exception {
         
        
    	Date date = java.util.Calendar.getInstance().getTime();
        Usuario usuario = new Usuario(documento);
        System.out.println(usuario);
        String pClaves = "";
        System.out.println(palabrasClave);
        for(String s:palabrasClave){     
        	 System.out.println(s);
            if(palabrasClave.indexOf(s)== palabrasClave.size()-1){
                pClaves = pClaves +s;
            }else   pClaves = pClaves +s+",";            
        }
        System.out.println(usuario.getDocumento());
        int id = this.calcularID();
        System.out.println(usuario.getDocumento());

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Iniciativa  iniciativa = new Iniciativa(id,"En_Espera",nombre,descripcion,date,pClaves,usuario,area); 
        
        System.out.println(iniciativa);
		serviciosBancoIniciativa.InsertarIniciativa(iniciativa);
		FacesMessage msg;
        msg = new FacesMessage("Insertada una iniciativa Exitosamente");
        FacesContext.getCurrentInstance().addMessage(null, msg); 
	
        palabrasClave.clear();
		
    }
    
    public void consultarIniciativas(){
        ArrayList<Iniciativa> iniciativas = serviciosBancoIniciativa.consultarIniciativas();
        ArrayList<Iniciativa> iniciativasPalClaves = new ArrayList<Iniciativa>();
        
        for(Iniciativa i: iniciativas){
            for(String s: palabrasClaveConsultar){
                System.out.println(i.getPalabrasClave());
                System.out.println("palabras claveeeeeeeeeeeeeeeee");
                if(i.getPalabrasClave().contains(s) && !iniciativasPalClaves.contains(i)){                    
                    iniciativasPalClaves.add(i);
                }
            }
        }
        palabrasClaveConsultar.clear();
        iniciativasClave = iniciativasPalClaves;
    }
    
    private int calcularID() {
    	try {
    		ArrayList<Iniciativa> iniciativas = serviciosBancoIniciativa.consultarIniciativas();
       	 	System.out.println(iniciativas);
	       	int maxId = 0;
	     	if (!iniciativas.isEmpty()) {
	     		for (Iniciativa ini: iniciativas) {
	         		if(ini.getId() > maxId) {
	         			maxId = ini.getId();
	         		}
         		}
         		
         	}
         	return maxId+1;
    	} catch (Exception ex) {
    		return 1;
    	}
    	
    	
    }
    
    public void agregarPalabrasClave(String nuevaPalabra) {
    	this.palabrasClave.add(nuevaPalabra);    	
    }
    
    public void agregarPalabrasClaveConsultar(String nuevaPalabra) {
    	this.palabrasClaveConsultar.add(nuevaPalabra);    	
    }
    
    
    public ArrayList<String> getpalabrasClave(){
		return this.palabrasClave;
    }
    
    public void setpalabrasClave(ArrayList<String> nuevasPalabras){
		this.palabrasClave=nuevasPalabras;
    }
    
    public ArrayList<String> getPalabrasClaveConsultar() {
        return palabrasClaveConsultar;
    }

    public void setPalabrasClaveConsultar(ArrayList<String> palabrasClaveConsultar) {
        this.palabrasClaveConsultar = palabrasClaveConsultar;
    }
    
    
    public ArrayList<Iniciativa> getIniciativasClave() {
        return iniciativasClave;
    }

    public void setIniciativasClave(ArrayList<Iniciativa> iniciativasClave) {
        this.iniciativasClave = iniciativasClave;
    }
    
}
