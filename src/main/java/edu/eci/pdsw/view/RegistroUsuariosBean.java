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
    public void registrarCliente(String nombre, int documento, String area, long telefono, String correo,String rol) throws Exception {
       // try {
    		System.out.println(nombre);
    		System.out.println(documento);
        	System.out.println(telefono);
        	System.out.println(area);
        	System.out.println(correo);
        	System.out.println(rol);
        	/*
            Usuario usuario = new Usuario(nombre, documento, area, telefono, correo, rol);
            serviciosBancoIniciativa.InsertarUsuario(usuario);*/
       /* } catch (ExcepcionBancoIniciativas ex) {
        	throw ex;
        }*/
    }

    

}
