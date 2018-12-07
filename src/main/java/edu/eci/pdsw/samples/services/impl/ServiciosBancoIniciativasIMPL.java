package edu.eci.pdsw.samples.services.impl;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ComentarioDAO;
import edu.eci.pdsw.sampleprj.dao.InteresDAO; 
import edu.eci.pdsw.sampleprj.dao.IniciativaDAO;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Interes;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Singleton
public class ServiciosBancoIniciativasIMPL implements ServiciosBancoIniciativas {
	
    @Inject
    private UsuarioDao usuarioDao;
    
    @Inject
    private IniciativaDAO iniciativaDAO;
    
    @Inject
    private ComentarioDAO comentarioDAO;
    
    @Inject 

    private InteresDAO interesDAO; 
   

    public ServiciosBancoIniciativasIMPL() {
    }
    /**
     * Inserta un usuario
     * @param usuario
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas {
            usuarioDao.save(usuario);
     }
    
    /**
     * Inserta una iniciativa
     * @param iniciativa
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void InsertarIniciativa(Iniciativa iniciativa) throws ExcepcionBancoIniciativas {
        iniciativaDAO.save(iniciativa);        
    }
    
    /**
     * En lista las iniciativas registradas
     * @return  ArrayList<Iniciativa> Retorna las iniciativas registradas
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public ArrayList<Iniciativa> consultarIniciativas() throws ExcepcionBancoIniciativas{        
        return iniciativaDAO.load();
    }
    
    /**
     * Actualiza los datos del usuario pertinente
     * @param documento
     * @param rol
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void updateUsuario(long documento, String rol) throws ExcepcionBancoIniciativas {
        usuarioDao.updateUsuario(documento, rol);       
    }
    
    /**
     * Enlista todos los usuarios existentes
     * @return List<Usuario> Lista con los usuarios existentes
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public List<Usuario> consultarUsuarios() throws ExcepcionBancoIniciativas {
        return usuarioDao.consultarUsuarios();
    }
    
    /**
     * Retorna las iniciativas que seran usadas por las estadisticas
     * @return List<Iniciativa> Lista de todas las iniciativas
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public List<Iniciativa> consultarIniciativasEst() throws ExcepcionBancoIniciativas {
        return iniciativaDAO.consultarIniciativas();
    }
    
    /**
     * Actualiza la el estado de la iniciativa pertinente
     * @param estado
     * @param id
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void updateEstadoIniciativa(String estado, int id) throws ExcepcionBancoIniciativas {
        iniciativaDAO.updateEstadoIniciativa(estado, id);
    }
    
    /**
     * Consulta el usuario deacuerdo a un identificador
     * @param user
     * @return Usuario usuario el cual es identificado por user
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public Usuario obtenerUsuario(String user) throws ExcepcionBancoIniciativas{
        return usuarioDao.compararUsuario(user);
    }
    
    /**
     * Inserta un comentario a la iniciativa correspondiente
     * @param com
     * @param idIni
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void InsertarComentario(Comentario com, int idIni) throws ExcepcionBancoIniciativas {
       
        comentarioDAO.save(com, idIni);
    }
    
    /**
     * Consulta los comentarios de la iniciativa 
     * @param id
     * @return ArrayList<Comentario> Lista de todos los comentarios de la iniciativa identificada
     *          por el parametro id
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public ArrayList<Comentario> consultarComentarios(int id) throws ExcepcionBancoIniciativas {
        return comentarioDAO.load(id);
    }

    /**
     * Actualiza la descripcion de la iniciativa correspondiente
     * @param descripcion
     * @param id
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void cambiarDescripcionIniciativa(String descripcion, int id)throws ExcepcionBancoIniciativas{    	
    	iniciativaDAO.cambiarDescripcionIniciativa(descripcion,id);	
    }
    
    /**
     * 
     * @param id
     * @return  
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public Iniciativa consultarIniciativa(int id) throws ExcepcionBancoIniciativas {

        return iniciativaDAO.consultarIniciativa(id);
    }
    
    /**
     * 
     * @param nombre
     * @param id
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void cambiarNombreIniciativa(String nombre, int id) throws ExcepcionBancoIniciativas {
        iniciativaDAO.cambiarNombreIniciativa(nombre, id);
    }

    /**
     * 
     * @param documento
     * @param usuario
     * @return
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public Interes consultarInteres(int documento, long usuario) throws ExcepcionBancoIniciativas { 
        return interesDAO.consultarInteres(documento, usuario); 
    } 

    /**
     * 
     * @param documento
     * @param usuario
     * @param like
     * @throws ExcepcionBancoIniciativas 
     */
    @Override 

    public void updateInteres(int documento, long usuario, boolean like) throws ExcepcionBancoIniciativas { 
        interesDAO.updateInteres(documento, usuario, like); 
    } 

    /**
     * 
     * @param documento
     * @param usuario
     * @param interes
     * @throws ExcepcionBancoIniciativas 
     */
    @Override 
    public void agregarInteres(int documento, long usuario, Interes interes) throws ExcepcionBancoIniciativas { 
        interesDAO.agregarInteres(documento, usuario, interes); 
    } 
    
    /**
     * 
     * @param ini
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public void agregarIniciativaTest(Iniciativa ini) throws ExcepcionBancoIniciativas {
        iniciativaDAO.saveTest(ini);
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public Iniciativa LikesUnaIniciativa(int id) throws ExcepcionBancoIniciativas {
        return iniciativaDAO.LikesUnaIniciativa(id);
    }
    
    /**
     * 
     * @param documento
     * @return
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public Usuario consultarUsuario(long documento) throws ExcepcionBancoIniciativas {
        try {
            return usuarioDao.consultarUsuario(documento);
        } catch (PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al consultar el usuario ", e);
        }
    }

    /**
     * 
     * @param documento
     * @return
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public List<Iniciativa> iniciativasUnUsuario(long documento) throws ExcepcionBancoIniciativas {
    	try {
    		return iniciativaDAO.iniciativasUnUsuario(documento);
        }
        catch(PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al consultar el usuario ", e);
    	}
    }
    
    /**
     * 
     * @param user
     * @param password
     * @return
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public boolean autorizacionLogin(String user, String password) throws ExcepcionBancoIniciativas {
        Usuario usuario = usuarioDao.compararUsuario(user);
        boolean credenciales = false;
        if (!(usuario == null)) {
            if (usuario.getCorreo().equals(user) 
                    && Integer.toString((int) usuario.getDocumento()).equals(password)) 
                credenciales = true;
            if (credenciales == false) return credencialesIncorrectos();
            else return true;
        } else {
            return credencialesIncorrectos();
        }
    }
    
    /**
     * 
     * @return
     * @throws ExcepcionBancoIniciativas 
     */
    @Override
    public long consultarMax() throws ExcepcionBancoIniciativas {
        long max= 0;
        if (comentarioDAO.consultaMax().isEmpty()){
                return 1;
            }
        else{
        for (Comentario i : comentarioDAO.consultaMax()){
            max = max<i.getCommentId()? i.getCommentId():max;
        }
        }
        return max+1;
    }
 
    private boolean credencialesIncorrectos(){
        FacesMessage msg;
        msg = new FacesMessage("Credenciales Incorrecto");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return true;
    }
    
    

}
