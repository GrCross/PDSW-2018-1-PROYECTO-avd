package edu.eci.pdsw.view;

import edu.eci.pdsw.samples.services.ServiciosBancoIniciativasFactory;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

import java.util.ArrayList;
import java.util.Date;
import javax.faces.event.ActionEvent;

@SuppressWarnings("deprecation")
@ManagedBean(name = "insertUsersBean")
@SessionScoped
public class RegistroUsuariosBean extends BasePageBean {

    @Inject
    private ServiciosBancoIniciativas serviciosBancoIniciativa; 

      /**
     *
     * @param nombre
     * @param documento
     * @param direccion
     * @param telefono
     * @param email
     * @throws Exception
     */
    public void registrarCliente(String nombre, int documento, String area, String telefono, String correo,String rol) throws Exception {
        try {
            Usuario usuario = new Usuario(nombre, documento, area, telefono, correo, rol);
            serviciosBancoIniciativa.InsertarUsuario(usuario);
        } catch (ExcepcionBancoIniciativas ex) {
            throw ex;
        }
    }

    

}
