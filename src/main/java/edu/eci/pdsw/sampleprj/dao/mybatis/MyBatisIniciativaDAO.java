/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.IniciativaDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.IniciativaMapper;
import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.samples.entities.Iniciativa;

/**
 *
 * @author rosal
 */
public class MyBatisIniciativaDAO implements IniciativaDAO {
    
    @Inject
    private IniciativaMapper iniciativaMapper;
    
    @Override 
    public void save (Iniciativa ini) throws PersistenceException{
    	System.out.println(ini);
    	iniciativaMapper.agregarIniciativa(ini);
        
    }
    
}
