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
public class LoginBean implements Serializable {

    private String username;
    private String password;
    
    
    private ServiciosBancoIniciativasIMPL serviciosImpl= new ServiciosBancoIniciativasIMPL();

    /**
     * Managed bean que se encarga de la validar la autenticación de usuarios
     */
    public LoginBean() {

    }
    
    public void doLogin(String username2) {
    	System.out.println("aaaaaaaaaaaaaaaaaxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    	boolean login;
		try {
			System.out.println("___________________________username____________"+username2);
			login = serviciosImpl.autorizacionLogin("correo@correo");
			System.out.println("___________________________login____________");
			System.out.println(login);
			if(login) {
				System.out.println("Entra al if");
				Rol rol = serviciosImpl.tipoUsuario(username2);
				System.out.println("rol del if");
				System.out.println("DFWQqwdwqdqwqwdqwddqwqw");
				FacesContext.getCurrentInstance().getExternalContext().redirect("urlToRedirectTo");
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
