package edu.eci.pdsw.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasIMPL;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean extends BasePageBean implements Serializable {

    private String username;
    private String password;
    private Usuario usuario;
    
    
    @Inject
    private ServiciosBancoIniciativas serviciosImpl;

    /**
     * Managed bean que se encarga de la validar la autenticación de usuarios
     */
    public LoginBean() {

    }
    
    public void doLogin() {
    	boolean login;
		try {
			login = serviciosImpl.autorizacionLogin(username,password);
			if(login) {
				/*Rol rol = serviciosImpl.tipoUsuario(username2);
				System.out.println("rol del if");
				System.out.println("DFWQqwdwqdqwqwdqwddqwqw");*/
				FacesContext.getCurrentInstance().getExternalContext().redirect("perfilesUsuarios.xhtml");
                                usuario = serviciosImpl.obtenerUsuario(username);

			}
		} catch (ExcepcionBancoIniciativas e) {			
			System.out.println("rrrrrrrrrrrrrrrrrr77777777777777");
		} catch (IOException e) {
			System.out.println("bbbbbbbbbbbbbbbb77777777777777");
		}
    	
    	
    	
    } 
    
    public void redirect(String pagina){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(pagina+".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean filtroAdministrador(){
        
        if (compararPermisos(Rol.ADMINISTRADOR)){
            return true;
        }
        else {return false;}
    }
    
    public boolean filtroProponente(){
        
        if (compararPermisos(Rol.ADMINISTRADOR) || compararPermisos(Rol.PROPONENTE)){
            return true;
        }
        else {return false;}
    }
    
    public boolean filtroPMO(){
        
        if (compararPermisos(Rol.ADMINISTRADOR) || compararPermisos(Rol.PMO_ODI)){
            return true;
        }
        else {return false;}
       
    }
    
    private boolean compararPermisos(Rol rolComparar){
        Rol rol=usuario.getRol();
        if (rol.equals(rolComparar)){
            return true;
        }
        else {return false;}
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    /**
     * 
     * @return El usuario de ingreso
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param login Usuario
     */
    public void setUsername(String login) {
        this.username = login;
    }

    /**
     *
     * @return la contraseña usada para el login
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param senha Contraseña del login
     */
    public void setPassword(String senha) {
        this.password = senha;
    }

  


}
