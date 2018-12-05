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
    	
    	iniciativaMapper.agregarIniciativa(ini);        
    }

	@Override
	public List<Iniciativa> consultarIniciativas() throws PersistenceException {
		return iniciativaMapper.consultarIniciativas();
	}

	@Override
	public void updateEstadoIniciativa(String estado, int id) throws PersistenceException {
		iniciativaMapper.updateEstadoIniciativa(estado, id);
		
	}
    
    @Override
    public ArrayList<Iniciativa> load() throws PersistenceException{
       return iniciativaMapper.buscarIniciativas();
    }

    @Override
    public List<Iniciativa> iniciativasUnUsuario(long documento) throws PersistenceException {
        return iniciativaMapper.iniciativasUnUsuario(documento);
    }
    
    @Override
    public void cambiarDescripcionIniciativa(String descripcion,int id) {

    	iniciativaMapper.cambiarDescripcionIniciativa(descripcion,id);  	
    }
    @Override
    public Iniciativa consultarIniciativa(int id) throws PersistenceException{
    	return iniciativaMapper.consultarIniciativa(id);
    	
    }

    @Override
    public void cambiarNombreIniciativa(String nombre, int id) {
        iniciativaMapper.cambiarNombreIniciativa(nombre, id);
    }

    @Override
    public void saveTest(Iniciativa ini) throws PersistenceException {
        iniciativaMapper.agregarIniciativaTest(ini);
    }
    
            
    
}
