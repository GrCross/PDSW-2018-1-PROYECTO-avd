package edu.eci.pdsw.sampleprj.dao;

import org.apache.ibatis.exceptions.PersistenceException;

import edu.eci.pdsw.samples.entities.Usuario;
import java.util.List;

public interface UsuarioDao {
	public void save(Usuario us) throws PersistenceException;
        public Usuario consultarUsuario(long documento) throws PersistenceException;
        public void updateUsuario(long documento, String rol) throws PersistenceException;
        public List<Usuario> consultarUsuarios () throws PersistenceException;
        public int compararUsuario(String correo) throws PersistenceException;
    	
    	public String getRolusuario(String correo) throws PersistenceException;
}
