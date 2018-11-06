package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ComentarioMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.InteresMapper;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

public class MyBatisUsuarioDao implements UsuarioDao {

	@Inject
    private UsuarioMapper usuarioMapper;
	
	public MyBatisUsuarioDao() {
		System.out.println("creacionfewfewfewfewfewfe");
	}

	@Override
	public void save(Usuario us) throws PersistenceException {
		usuarioMapper.agregarUsuario(us);
	}

	@Override
	public Usuario load(String correo) throws PersistenceException {
		return usuarioMapper.consultarUsuario(correo);
	}

	

	@Override
	public int compararUsuario(String correo) throws PersistenceException {
		System.out.println("fewqfewegwerewwe");
		return usuarioMapper.compararUsuario(correo);
	}

	@Override
	public List<Usuario> consultarUsuarios() throws PersistenceException {
		
		return usuarioMapper.consultarUsuarios();
	}

	@Override
	public String getRolusuario(String correo) throws PersistenceException {		
		System.out.println("ñññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññññ");
		return usuarioMapper.getRolUsuario(correo);
	}

}
