package edu.eci.pdsw.samples.services.impl;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;

import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

@Singleton
public class ServiciosBancoIniciativasIMPL implements ServiciosBancoIniciativas {
	
	  @Inject
	   private UsuarioDao usuarioDao;

	public ServiciosBancoIniciativasIMPL() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas {
		try {
			usuarioDao.save(usuario);
	       } catch (PersistenceException ex) {
	           throw new ExcepcionBancoIniciativas("Error al registrar el cliente" +usuario.getDocumento(),ex);
	       }
	}

}
