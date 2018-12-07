package edu.eci.pdsw.view;
import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
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
@ManagedBean(name = "perfilUsuariosBean")
@SessionScoped
public class perfilUsuariosBean extends BasePageBean {    
    
    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa; 
    
    private long documentoUsuario = 88888;
    private Usuario usuario;
    private Usuario visitado;
    
    
    
    
    
    public void consultarUsuario() throws Exception{
        
        this.usuario=serviciosBancoIniciativa.consultarUsuario(documentoUsuario);
    } 

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(long documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }
    
    
}
