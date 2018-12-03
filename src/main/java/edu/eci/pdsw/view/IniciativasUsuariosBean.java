package edu.eci.pdsw.view;
import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Interes;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.view.BasePageBean;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.persistence.PersistenceException;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

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
    private ArrayList<String> palabrasClave = new ArrayList<String>();
    private ArrayList<String> palabrasClaveConsultar = new ArrayList<String>();
    private ArrayList<Iniciativa> iniciativasClave = new ArrayList<Iniciativa>();
    private ArrayList<String> palabrasClaveAll = new ArrayList<String>();

    
    
    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa; 
    
    public void limpiar(){

        //Antes de  hacer  render se llama a este metodo
        //Limpiara los objetos o propiedades
    	palabrasClave.clear();
    	palabrasClaveConsultar.clear();
    	
      }
    
    public void registrarIniciativa (String nombre, String descripcion,String area,long documento) throws Exception {
         
        
    	Date date = java.util.Calendar.getInstance().getTime();
        Usuario usuario = new Usuario(documento);
        String pClaves = "";
        
        for(String s:palabrasClave){     
        
            if(palabrasClave.indexOf(s)== palabrasClave.size()-1){
                pClaves = pClaves +s;
            }else   pClaves = pClaves +s+",";            
        }
        int id = this.calcularID();
        Iniciativa  iniciativa = new Iniciativa(id,"En_Espera",nombre,descripcion,date,pClaves,usuario,area); 
        
        
		serviciosBancoIniciativa.InsertarIniciativa(iniciativa);
		FacesMessage msg;
        msg = new FacesMessage("Insertada una iniciativa Exitosamente");
        FacesContext.getCurrentInstance().addMessage(null, msg); 
	
        palabrasClave.clear();
		
    }
    
    public void votar(int iniciativa , long documento) throws Exception {
        System.out.println(iniciativa);
        try {
            Interes interes = serviciosBancoIniciativa.consultarInteres(iniciativa, documento);
            System.out.println(iniciativa+"------- "+ documento+ "       " + interes);
            if (interes.getVoto()==false){
                serviciosBancoIniciativa.updateInteres(iniciativa, documento, true);
            } else {
                serviciosBancoIniciativa.updateInteres(iniciativa, documento, false);
            }
            
        } catch (Exception ex) {
            
            Interes interes = new Interes (iniciativa, 1,true);
            serviciosBancoIniciativa.agregarInteres(iniciativa, documento, interes);
        }    
    }
    
    public boolean like (int iniciativa , long documento) {
        try {
            Interes interes = serviciosBancoIniciativa.consultarInteres(iniciativa, documento);
            return interes.getVoto();
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    public void consultarIniciativas(){
        ArrayList<Iniciativa> iniciativas = serviciosBancoIniciativa.consultarIniciativas();
        ArrayList<Iniciativa> iniciativasPalClaves = new ArrayList<Iniciativa>();
        
        for(Iniciativa i: iniciativas){
            System.out.println("++++++++++++++++++++++++++++++++++++" + i.getVotos() + "jesus");
            for(String s: palabrasClaveConsultar){
                if(i.getPalabrasClave().contains(s) && !iniciativasPalClaves.contains(i)){                    
                    iniciativasPalClaves.add(i);
                    
                }
            }
        }if(iniciativasPalClaves.isEmpty()){
            iniciativasClave = iniciativas;
        }else{
            palabrasClaveConsultar.clear();
            iniciativasClave = iniciativasPalClaves;
        }        
    }
    
    public void consultarPalabrasClave(){
        ArrayList<Iniciativa> iniciativas = serviciosBancoIniciativa.consultarIniciativas();        
        for(Iniciativa i: iniciativas){
            
            String[] temp = i.getPalabrasClave().split("\\W+");
            for (int j = 0; j <temp.length ; j++) {
                palabrasClaveAll.add(temp[j]);
            }
        }
    }
    
    private int calcularID() {
    	   try {
            ArrayList<Iniciativa> iniciativas = serviciosBancoIniciativa.consultarIniciativas();
            
            int maxId = 0;
            if (!iniciativas.isEmpty()) {
                for (Iniciativa ini : iniciativas) {
                    if (ini.getId() > maxId) {
                        maxId = ini.getId();
                    }
                }

            }
            return maxId + 1;
        } catch (Exception ex) {
            return 1;
        }
    }
    
    public void redirect(String pagina){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina+".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<String> getPalabrasClaveAll() {
        return palabrasClaveAll;
    }

    public void setPalabrasClaveAll(ArrayList<String> palabrasClaveAll) {
        this.palabrasClaveAll = palabrasClaveAll;
    }
    
}
