package edu.eci.pdsw.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasIMPL;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean extends BasePageBean implements Serializable {

    private String username;
    private String password;
    
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
			login = serviciosImpl.autorizacionLogin(username);
			if(login) {
				/*Rol rol = serviciosImpl.tipoUsuario(username2);
				System.out.println("rol del if");
				System.out.println("DFWQqwdwqdqwqwdqwddqwqw");*/
				FacesContext.getCurrentInstance().getExternalContext().redirect("consultaEntradasForo.xhtml");
			}
		} catch (ExcepcionBancoIniciativas e) {
			
			System.out.println("rrrrrrrrrrrrrrrrrr77777777777777");
		} catch (IOException e) {
				
			System.out.println("bbbbbbbbbbbbbbbb77777777777777");
		}
    	
    	
    	
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
