package edu.eci.pdsw.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;

import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean extends BasePageBean implements Serializable {

    private String username;
    private String password;
    private Usuario usuario;
    private Usuario visitado;    
    private String pagina;
    
    
	@Inject
    private ServiciosBancoIniciativas serviciosImpl;

    /**
     * Managed bean que se encarga de la validar la autenticación de usuarios
     */
    public LoginBean() {
    	usuario=null;
    	visitado=null;
    	

    }
    
    public void doLogin() {
        boolean login;
        try {
            login = serviciosImpl.autorizacionLogin(username, password);
            if (login) {
                usuario = serviciosImpl.obtenerUsuario(username);
                FacesContext.getCurrentInstance().getExternalContext().redirect("perfilesUsuarios.xhtml");

            }
        } catch (ExcepcionBancoIniciativas e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    public void doLogOut() {
    	usuario=null;
    	redirect("Inicio");
    	
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
        else return false; 
    }
    
    public boolean filtroProponente(){
        if (compararPermisos(Rol.ADMINISTRADOR) ||
                compararPermisos(Rol.PROPONENTE)){
            return true;
        }
        else return false;
    }
    
    public boolean filtroPMO(){

        if (compararPermisos(Rol.ADMINISTRADOR) || 
                compararPermisos(Rol.PMO_ODI)){
            return true;
        }
        else return false;
       
    }
    
    private boolean compararPermisos(Rol rolComparar){
    	if(!(usuario==null)) {
	        Rol rol=usuario.getRol();
        	if (rol.equals(rolComparar))return true;
        	else return false;
    	}
    	else return false;
        
    }
    
    public void autorizacion(){ 
    	
    	String auth2 =FacesContext.getCurrentInstance().getViewRoot().getViewId();
    	String auth = auth2.substring(1, auth2.length()-6);
    	if(auth.equals("Inicio")){
            if(!(usuario==null)) redirect("Inicio");
            else redirect("perfilesUsuarios");
    	}
        else if (!(usuario == null)) {
            Rol usuarioRol = usuario.getRol();
            if (!(auth.equals("visitante") && visitado == null)) {
                redireccion(usuarioRol, auth);
            } else redirect("perfilesUsuarios");            
        } else redirect("Inicio");
    }
    
    private boolean level(int nivel,String auth) {
        switch (nivel) {
            case 1:
                if((auth.equals("registrarIniciativas") || 
                        auth.equals("actualizarEstadoIniciativas") || 
                            auth.equals("busquedaUsuario") )) {
                    return true;
                }
                break;
            case 2:
                if( auth.equals("actualizarEstadoIniciativas") || 
                        auth.equals("busquedaUsuario")) {
                    return true;
                }
                break;
            case 3:
                if(auth.equals("registrarIniciativas")) {
                    return true;
                }
                break;
            default:
                break;
        }
    	return false;
    		
    	}
    
    public List<Iniciativa> iniciativasUnUsuario () throws Exception {
        return serviciosImpl.iniciativasUnUsuario(usuario.getDocumento());
    }
    
    public List<Iniciativa> iniciativasUnVisitado() throws Exception {
        return serviciosImpl.iniciativasUnUsuario(visitado.getDocumento());
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

    public Usuario getVisitado() {
        return visitado;
    }

    public void setVisitado(Usuario visitado) {
        this.visitado = visitado;
    }
    
    
    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
        
    
    private void redireccion(Rol usuarioRol,String auth){
        if (!(usuarioRol.equals(Rol.ADMINISTRADOR))) {

            if (usuarioRol.equals(Rol.USUARIO_DE_CONSULTA) && level(1, auth)) {
                redirect("consultarIniciativas");
            } else if (usuarioRol.equals(Rol.PROPONENTE) && level(2, auth)) {
                redirect("perfilesUsuarios");
            } else if (usuarioRol.equals(Rol.PMO_ODI) && level(3, auth)) {
                redirect("perfilesUsuarios");
            }
        }
    }

}
