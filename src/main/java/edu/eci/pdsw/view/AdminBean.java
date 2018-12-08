/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.view;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Estado;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.util.ArrayList;
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
@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean extends BasePageBean {

    private final Rol[] roles = Rol.values();
    private ArrayList<String> rolesString = Rol.arreglo();
    private Rol rolCambio;
    private Estado[] estados = Estado.values();


    private List<Usuario> Busuario = null;

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa;

    public List<Usuario> consultarUsuarios() {

        try {
            Busuario = serviciosBancoIniciativa.consultarUsuarios();
        } catch (Exception ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Busuario;
    }

    public void updateUsuario(long documento, Rol nuevoRol) {
        
        try {
            
            serviciosBancoIniciativa.updateUsuario(documento,nuevoRol.toString() );
            FacesMessage msg;
            msg = new FacesMessage("Actualizado");
            FacesContext.getCurrentInstance().addMessage(null, msg); 
        } catch (Exception ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Iniciativa> consultarIniciativas() {
    	List<Iniciativa> BeanIniciativas = null;
    	 try {
    		 BeanIniciativas= serviciosBancoIniciativa.consultarIniciativas();
    		
    	 } catch (Exception ex) {
             Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
         }
    	 return BeanIniciativas;
    }
    
    public void updateEstadoIniciativa (Estado estado, int id) {
    	
    	try {
            serviciosBancoIniciativa.updateEstadoIniciativa(estado.toString(),id );
            FacesMessage msg;
            msg = new FacesMessage("Actualizado el estado");
            FacesContext.getCurrentInstance().addMessage(null, msg); 
        } catch (Exception ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }

    public List<Usuario> getBusuario() {
        return Busuario;
    }

    public void setBusuario(List<Usuario> Busuario) {
        this.Busuario = Busuario;
    }

    public Rol[] getRoles() {
        return roles;
    }

    public void setRoles(Rol[] roles1) {

    }
    
    public ArrayList<String> getRolesString() {
        return rolesString;
    }

    public void setRolesString(ArrayList<String> rolesString) {
        this.rolesString = rolesString;
    }
    
    public Rol getRolCambio() {
        return rolCambio;
    }

    public void setRolCambio(Rol rolCambio) {
        this.rolCambio = rolCambio;
    }    
    
     public Estado[] getEstados() {
        return estados;
    }

    public void setEstados(Estado[] estados) {
        this.estados = estados;
    }
    

}
