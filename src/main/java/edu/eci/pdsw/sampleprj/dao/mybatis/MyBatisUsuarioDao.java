package edu.eci.pdsw.sampleprj.dao.mybatis;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ComentarioMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.InteresMapper;
import edu.eci.pdsw.samples.entities.Usuario;
import java.util.List;

public class MyBatisUsuarioDao implements UsuarioDao {

	@Inject
    private UsuarioMapper usuarioMapper;

	@Override
	public void save(Usuario us) throws PersistenceException {
		usuarioMapper.agregarUsuario(us);
	}

    @Override
    public Usuario consultarUsuario(long documento) throws PersistenceException {
        return usuarioMapper.consultarUsuario(documento);
        
    }


    @Override
    public void updateUsuario(long documento, String rol) throws PersistenceException {
        usuarioMapper.updateUsuario(documento,rol);
    }

    @Override
    public List<Usuario> consultarUsuarios() throws PersistenceException {
        return usuarioMapper.consultarUsuarios();
    }
        
        

}
