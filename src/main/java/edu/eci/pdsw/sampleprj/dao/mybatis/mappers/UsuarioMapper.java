package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Usuario;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface UsuarioMapper {
	
		@Transactional
	    public void agregarUsuario(@Param("u") Usuario usu);	

}
