/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 */
public class Iniciativa implements Serializable{
    private int id;
    private int estado;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private ArrayList<String> palabrasClave;
    private Usuario autor;
    private ArrayList<Comentario> comentarios;

    public Iniciativa(int id, int estado, String nombre, String descripcion, ArrayList<Interes> interasados,ArrayList<Comentario>comentarios,ArrayList<String> palabrasClave,Usuario autor) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.palabrasClave = palabrasClave;
        this.comentarios = comentarios;
        this.autor = autor;
        
    }

    public Iniciativa() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    

    public Usuario getUsuario_documento() {
        return autor;
    }

    public void setUsuario_documento(Usuario autor) {
        this.autor = autor;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    

    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setpalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
    
    
    public ArrayList<Comentario> getComentarios(){
    	return comentarios;
    }
    public void setComentarios(ArrayList<Comentario> comentarios) {
    	this.comentarios = comentarios;
    }


    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", estado=" + estado + ", nombre=" + nombre + ",descripcion=" + descripcion + "fechaCreacion=" + descripcion + "descripcion=" + descripcion+"}";
    }


    
    
    
    
}
