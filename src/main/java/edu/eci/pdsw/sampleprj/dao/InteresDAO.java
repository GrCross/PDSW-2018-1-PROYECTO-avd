/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Interes;
import java.util.List;
import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2134840
 */
public interface InteresDAO {
    
    public Interes consultarInteres( int documento,long usuario ) throws PersistenceException;
    
    public void updateInteres (int documento,long usuario, boolean like  )throws PersistenceException;
    
    public void agregarInteres (int documento,long usuario,Interes interes) throws PersistenceException;
    
    
}
