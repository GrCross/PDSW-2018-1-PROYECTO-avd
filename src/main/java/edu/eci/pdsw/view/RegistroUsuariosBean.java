package edu.eci.pdsw.view;




import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;


import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;



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
     * @param area
     * @param correo
     * @param rol
     * @param telefono
     * @throws Exception
     */
    public void registrarCliente(String nombre, int documento, String area, int telefono, String correo,String rol) throws Exception {
        try {
            Usuario usuario = new Usuario(nombre, documento, area, telefono, correo, rol);
            serviciosBancoIniciativa.InsertarUsuario(usuario);
        } catch (ExcepcionBancoIniciativas ex) {
        	throw ex;
        }
    }

    

}
