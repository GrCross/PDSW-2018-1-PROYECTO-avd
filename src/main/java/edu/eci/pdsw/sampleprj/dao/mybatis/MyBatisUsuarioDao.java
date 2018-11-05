package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ComentarioMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.InteresMapper;
import edu.eci.pdsw.samples.entities.Usuario;

public class MyBatisUsuarioDao implements UsuarioDao {

	@Inject
    private UsuarioMapper usuarioMapper;

	@Override
	public void save(Usuario us) throws PersistenceException {
		usuarioMapper.agregarUsuario(us);
	}

}
