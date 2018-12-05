package edu.eci.pdsw.samples.services.impl;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.ComentarioDAO;
import edu.eci.pdsw.sampleprj.dao.IniciativaDAO;
import edu.eci.pdsw.sampleprj.dao.InteresDAO;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Interes;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;

import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.util.ArrayList;
import java.util.Collections;
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
		// TODO Auto-generated constructor stub
    }

    @Override
    public void InsertarUsuario(Usuario usuario) throws PersistenceException {
        
            usuarioDao.save(usuario);
        
	
     }

    @Override
    public void InsertarIniciativa(Iniciativa iniciativa) throws PersistenceException {
        iniciativaDAO.save(iniciativa);        
    }
    
    @Override
    public ArrayList<Iniciativa> consultarIniciativas() throws PersistenceException{        
        return iniciativaDAO.load();
    }

    @Override
    public Usuario consultarUsuario(long documento) throws ExcepcionBancoIniciativas {
       try {
        return usuarioDao.consultarUsuario(documento);
        } catch (PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al consultar el usuario ", e);
        }
    }

    @Override
    public void updateUsuario(long documento, String rol) throws PersistenceException {
        
        usuarioDao.updateUsuario(documento, rol);
       
    }

    @Override
    public List<Usuario> consultarUsuarios() throws Exception {
        return usuarioDao.consultarUsuarios();
    }

	@Override
	public List<Iniciativa> consultarIniciativasEst() throws Exception {
		return iniciativaDAO.consultarIniciativas();
	}

	@Override
	public void updateEstadoIniciativa(String estado, int id) throws Exception {
		iniciativaDAO.updateEstadoIniciativa(estado, id);
		
	}

	@Override
	public boolean autorizacionLogin(String user, String psw) throws ExcepcionBancoIniciativas {
		Usuario usuario = usuarioDao.compararUsuario(user);
		boolean credenciales =false;
		if(!(usuario==null)) {
			if(usuario.getCorreo().equals(user) && Integer.toString((int) usuario.getDocumento()).equals(psw)) {
				credenciales=true;
			}
			
	        if(credenciales==false) {
	                    FacesMessage msg;
	                    msg = new FacesMessage("Credenciales Incorrecto");
	                    FacesContext.getCurrentInstance().addMessage(null, msg);
	                    return false;
				}
			
			else {
	                    return true;
            }
		}
		else {
			 FacesMessage msg;
             msg = new FacesMessage("Credenciales Incorrecto");
             FacesContext.getCurrentInstance().addMessage(null, msg);
			 return false;
		 }
	}
        @Override
        public Usuario obtenerUsuario(String user){
            return usuarioDao.compararUsuario(user);
        }

	@Override
	public Rol getRolUsuario(String correo) throws ExcepcionBancoIniciativas {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public void InsertarComentario(Comentario com, int idIni) throws Exception {
        System.out.println(com);
        comentarioDAO.save(com, idIni);
    }
    
    @Override
    public ArrayList<Comentario> consultarComentarios(int id) throws Exception {
        return comentarioDAO.load(id);
    }

    @Override
    public long consultarMax() throws Exception {
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

    @Override
    public List<Iniciativa> iniciativasUnUsuario(long documento) throws Exception {
    	try {
    		return iniciativaDAO.iniciativasUnUsuario(documento);
        }
		catch(Exception e) {
			return null;
    	}
    	
    }
    
    @Override
    public void cambiarDescripcionIniciativa(String descripcion, int id) {    	
    	iniciativaDAO.cambiarDescripcionIniciativa(descripcion,id);
    	
    }

	@Override
	public Iniciativa consultarIniciativa(int id) throws Exception {
		
		return iniciativaDAO.consultarIniciativa(id);
	}

    @Override
    public void cambiarNombreIniciativa(String nombre, int id) throws Exception {
        iniciativaDAO.cambiarNombreIniciativa(nombre, id);
    }

    @Override
    public Interes consultarInteres(int documento, long usuario) throws Exception {
        return interesDAO.consultarInteres(documento, usuario);
    }

    @Override
    public void updateInteres(int documento, long usuario, boolean like) throws Exception {
        interesDAO.updateInteres(documento, usuario, like);
    }

    @Override
    public void agregarInteres(int documento, long usuario, Interes interes) throws Exception {
        interesDAO.agregarInteres(documento, usuario, interes);
    }

    @Override
    public Iniciativa LikesUnaIniciativa(int id) throws Exception {
        return iniciativaDAO.LikesUnaIniciativa(id);
    }

 
    
    

}
