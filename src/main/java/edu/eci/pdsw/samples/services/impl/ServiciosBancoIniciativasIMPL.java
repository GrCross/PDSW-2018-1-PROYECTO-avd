package edu.eci.pdsw.samples.services.impl;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.IniciativaDAO;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;

import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ServiciosBancoIniciativasIMPL implements ServiciosBancoIniciativas {
	
    @Inject
    private UsuarioDao usuarioDao;
    
    @Inject
    private IniciativaDAO iniciativaDAO;

    public ServiciosBancoIniciativasIMPL() {
		// TODO Auto-generated constructor stub
    }

    @Override
    public void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas {
        try {
            usuarioDao.save(usuario);
        } catch (PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al registrar el usuario ", e);
        }
	
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
        System.out.println(documento+"-----"+rol);
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
	public void updateEstadoIniciativa(int estado, int id) throws Exception {
		iniciativaDAO.updateEstadoIniciativa(estado, id);
		
	}

}
