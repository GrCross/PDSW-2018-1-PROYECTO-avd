/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.pdsw.sampleprj.dao.IniciativaDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.IniciativaMapper;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import edu.eci.pdsw.samples.entities.Iniciativa;
import java.util.ArrayList;

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

	@Override
	public List<Iniciativa> consultarIniciativas() throws PersistenceException {
		return iniciativaMapper.consultarIniciativas();
	}

	@Override
	public void updateEstadoIniciativa(int estado, int id) throws PersistenceException {
		iniciativaMapper.updateEstadoIniciativa(estado, id);
		
	}
    
    @Override
    public ArrayList<Iniciativa> load() throws PersistenceException{
        System.out.println("nombre "+iniciativaMapper.buscarIniciativas().get(0).getNombre());
        System.out.println("id"+iniciativaMapper.buscarIniciativas().get(0).getId());
        System.out.println("palabrasClave "+iniciativaMapper.buscarIniciativas().get(0).getPalabrasClave());
        return iniciativaMapper.buscarIniciativas();
    }
            
    
}
