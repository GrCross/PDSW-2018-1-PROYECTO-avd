/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Iniciativa;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author rosal
 */
public interface IniciativaMapper {
    
    void agregarIniciativa(@Param("i") Iniciativa ini);
    
}
