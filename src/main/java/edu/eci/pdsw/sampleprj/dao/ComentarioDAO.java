/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao;

import edu.eci.pdsw.samples.entities.Comentario;
import java.util.ArrayList;
import org.apache.ibatis.exceptions.PersistenceException;

/**
 *
 * @author 2134840
 */
public interface ComentarioDAO {
    
    public void save (Comentario com, int idIni) throws PersistenceException;
    public ArrayList<Comentario> load(int id) throws PersistenceException;
}
