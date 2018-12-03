package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Interes;
import edu.eci.pdsw.samples.entities.Usuario;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

public interface UsuarioMapper {
	
		@Transactional
	    public void agregarUsuario(@Param("u") Usuario usu);	
            
            public Usuario consultarUsuario(@Param("doc") long documento);
            
            public void updateUsuario(@Param("doc") long documento, @Param("rol") String rol);
            
            public List<Usuario> consultarUsuarios();
            
            
            public Usuario compararUsuario(@Param("correousr") String correo);
            
            public String getRolUsuario(@Param("correousr") String id) ;
            
            public Interes consultarInteres (@Param("doc") int documento, @Param("usu") long usuario );
            
            public void updateInteres (@Param("doc") int documento, @Param("usu") long usuario, @Param("doc") boolean like );
            
            public void agregarInteres (@Param("doc") int documento, @Param("usu") long usuario, @Param("in") Interes interes );

}
