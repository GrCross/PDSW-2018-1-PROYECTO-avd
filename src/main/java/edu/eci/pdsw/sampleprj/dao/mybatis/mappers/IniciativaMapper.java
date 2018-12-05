/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.sampleprj.dao.mybatis.mappers;

import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Usuario;

import java.util.List;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author rosal
 */
public interface IniciativaMapper {
    
    public void agregarIniciativa(@Param("i") Iniciativa ini);
    
    public void agregarIniciativaTest(@Param("i") Iniciativa ini);
    
    ArrayList<Iniciativa> buscarIniciativas(); 
    
    public List<Iniciativa> consultarIniciativas();
    
    public void updateEstadoIniciativa(@Param("estado") String estado, @Param("id") int id);
    
    public List<Iniciativa> iniciativasUnUsuario (@Param("doc") long documento);
    
    
    //public void cambiarInformacionIniciativa(@Param("descripcion") String descripcion, @Param("palabrasclave") String palabrasclave,@Param("id") int id);
    public void cambiarDescripcionIniciativa(@Param("descripcion") String descripcion,@Param("id") int id);
    
    public void cambiarNombreIniciativa(@Param("nombre") String nombre,@Param("id") int id);
    
    public Iniciativa consultarIniciativa(@Param("id") int id);
   
}
