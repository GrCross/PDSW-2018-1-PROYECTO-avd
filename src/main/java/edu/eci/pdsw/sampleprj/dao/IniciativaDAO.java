/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Iniciativa;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author rosal
 */
public interface IniciativaDAO {
    
    public void save(Iniciativa ini) throws PersistenceException;
    
    /**
     * Lista todas las inciativas
     * @return Iniciativas
     * @throws PersistenceException
     */
    public List<Iniciativa>  consultarIniciativas() throws PersistenceException;
    
    /**
     * Actualiza el estado de una iniciativa
     * @param estado
     * @param id
     * @throws PersistenceException
     */
    public void updateEstadoIniciativa(int estado, int id) throws PersistenceException;
    
}
