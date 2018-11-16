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
            System.out.println("-------------------------------------------------------------------------------------------");
        Comentario comentario = new Comentario(usuario, fechaDeCreacion, contenido, id);
        
        
        try {
            serviciosBancoIniciativa.InsertarComentario(comentario,idIniciativa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Has añadido un comentario."));
        } catch (Exception ex) {
            Logger.getLogger(IniciativasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void consultarComentarios() throws Exception{
        comentarios = serviciosBancoIniciativa.consultarComentarios(iniciativa.getId());
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
