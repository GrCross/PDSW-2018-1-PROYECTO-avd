/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.time.LocalDate;
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
    private String palabrasClave;
    private Usuario autor;
    private ArrayList<Comentario> comentarios;
    private String area;

    public Iniciativa(int id, int estado, String nombre, String descripcion, Date fechaCreacion,ArrayList<Comentario>comentarios,String palabrasClave,Usuario autor,String area) {
        this.id = id;
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.palabrasClave = palabrasClave;
        this.comentarios = comentarios;
        this.autor = autor;
        this.area = area;
        
    }

    public Iniciativa() {
    }
    public Iniciativa(int id2, int estado2, String nombre2, String descripcion2, Date now,
			String palabrasClave2, Usuario usuario,String area) {
    	this.id = id2;
        this.estado = estado2;
        this.nombre = nombre2;
        this.descripcion = descripcion2;
        this.fechaCreacion = now;
        this.palabrasClave = palabrasClave2;
        this.autor = usuario;
        this.area = area;
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
    

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setpalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }
    
    
    public ArrayList<Comentario> getComentarios(){
    	return comentarios;
    }
    public void setComentarios(ArrayList<Comentario> comentarios) {
    	this.comentarios = comentarios;
    }
    
    public String getArea() {
        return area;
    }

    public void setAutor(Usuario area) {
        this.autor = area;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Item{" + " id= " + id + ", estado=" + estado + ", nombre=" + nombre + ",descripcion=" + descripcion + ", fechaCreacion=" + fechaCreacion + ", palabrasclave=" + palabrasClave+", autor="+autor+",area="+area+"}";
    }



    
    
    
    
}
