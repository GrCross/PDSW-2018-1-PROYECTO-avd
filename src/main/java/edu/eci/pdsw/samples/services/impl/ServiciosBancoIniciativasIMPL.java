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
    public void InsertarIniciativa(Iniciativa iniciativa) throws ExcepcionBancoIniciativas {
        try {
            iniciativaDAO.save(iniciativa);
        } catch (PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al registrar la iniciativa ", e);
        }
    }

}
