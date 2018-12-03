/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.InteresDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.UsuarioMapper;
import edu.eci.pdsw.samples.entities.Interes;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2134840
 */
public class MyBatisInteresDAO implements InteresDAO{
    
    @Inject
    private UsuarioMapper usuarioMapper;

    @Override
    public Interes consultarInteres(int documento, long usuario) throws PersistenceException {
        return usuarioMapper.consultarInteres(documento, usuario);
    }

    @Override
    public void updateInteres(int documento, long usuario, boolean like) throws PersistenceException {
        usuarioMapper.updateInteres(documento, usuario, like);
    }

    @Override
    public void agregarInteres(int documento, long usuario, Interes interes) throws PersistenceException {
        usuarioMapper.agregarInteres(documento, usuario, interes);
    }
    
}
