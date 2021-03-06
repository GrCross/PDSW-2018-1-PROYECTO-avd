/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;
import edu.eci.pdsw.samples.entities.Comentario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.guice.transactional.Transactional;

/**
 *
 * @author rosal
 */
public interface ComentarioMapper {
    
    
     public void agregarComentario(@Param("c") Comentario com, @Param("ini") int idIni);
    
     public ArrayList<Comentario> consultarComentarios(@Param("idIniciativa")int id);
     
     public    ArrayList<Comentario>  consultarMax ();
}
