package edu.eci.pdsw.sampleprj.dao;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;

public interface UsuarioDao {
	public void save(Usuario us) throws PersistenceException;
	
	public Usuario load(String correo) throws PersistenceException;
	
	public List<Usuario> consultarUsuarios() throws PersistenceException;
	
	public int compararUsuario(String correo) throws PersistenceException;
	
	public String getRolusuario(String correo) throws PersistenceException;
}
