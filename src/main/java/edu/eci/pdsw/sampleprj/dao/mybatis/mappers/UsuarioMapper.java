package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface UsuarioMapper {
	
		@Transactional
	    public void agregarUsuario(@Param("u") Usuario usu);	
		@Transactional
		public Usuario consultarUsuario(@Param("correousr") String id) ;
		@Transactional
		public List<Usuario> consultarUsuarios();
		@Transactional
		public int compararUsuario(@Param("correousr") String correo);
		@Transactional
		public String getRolUsuario(@Param("correousr") String id) ;
		
		
		

}
