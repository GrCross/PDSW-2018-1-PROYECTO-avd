/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.ComentarioDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ComentarioMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.pdsw.samples.entities.Comentario;
import java.util.ArrayList;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2134840
 */
public class MyBatisComentarioDAO implements ComentarioDAO{
    @Inject
    private ComentarioMapper comentarioMapper;

    @Override
    public void save(Comentario com, int idIni) throws PersistenceException{
        comentarioMapper.agregarComentario(com, idIni);
    }
    @Override
    public ArrayList<Comentario> load(int id) throws PersistenceException{
        return comentarioMapper.consultarComentarios(id);
    }

    @Override
    public ArrayList<Comentario> consultaMax() throws PersistenceException {        
        return comentarioMapper.consultarMax();
    }
}
