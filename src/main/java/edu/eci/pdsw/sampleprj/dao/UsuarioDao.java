package edu.eci.pdsw.sampleprj.dao;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Usuario;

public interface UsuarioDao {
	public void save(Usuario us) throws PersistenceException;
}
