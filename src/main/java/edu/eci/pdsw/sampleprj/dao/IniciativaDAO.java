/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Iniciativa;
import java.util.ArrayList;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author rosal
 */
public interface IniciativaDAO {
    
    public void save(Iniciativa ini) throws PersistenceException;
    public ArrayList<Iniciativa> load() throws PersistenceException;
    
}
