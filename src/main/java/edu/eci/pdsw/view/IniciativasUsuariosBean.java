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
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    
    
    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa; 
    
    /**
    *private int id;
    private int estado;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private ArrayList<String> palabrasClave;
    private Usuario autor;
    */
    public void registrarIniciativa (int id,int estado, String nombre, String descripcion ){
         
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Calendar today = Calendar.getInstance();
    	//today.set(Calendar.HOUR_OF_DAY, 0);
    	Date date = java.util.Calendar.getInstance().getTime();
        //no se registran las palabras claves, por el problema con postgresql (en los test, creo)
        Usuario usuario = new Usuario(documento);
        System.out.println(usuario);
        Iniciativa  iniciativa = new Iniciativa(id,estado,nombre,descripcion,date,palabrasClave,usuario); 
        try {
			serviciosBancoIniciativa.InsertarIniciativa(iniciativa);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void agregarPalabrasClave(String nuevaPalabra) {
    	this.palabrasClave.add(nuevaPalabra);
    	
    }
    
    public ArrayList<String> getpalabrasClave(){
		return this.palabrasClave;
	}
    
    public void setpalabrasClave( ArrayList<String> nuevasPalabras){
		this.palabrasClave=nuevasPalabras;
	}
    
}
