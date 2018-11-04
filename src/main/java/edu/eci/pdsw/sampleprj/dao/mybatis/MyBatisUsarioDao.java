package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import edu.eci.pdsw.samples.entities.Usuario;

public class MyBatisUsarioDao implements UsuarioDao {

	@Inject
    private UsuarioMapper usuarioMapper;

	@Override
	public void save(Usuario us) throws PersistenceException {
		usuarioMapper.agregarUsuario(us);
	}

}
