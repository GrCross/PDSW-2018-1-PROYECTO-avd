/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Array;

/**
 *
 */
public class Iniciativa implements Serializable{
    private int id;
    private int estado;
    private int usuario_documento;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private ArrayList<String> palabrasClave;
    private ArrayList<Interes> interasados;
    private ArrayList<Comentario> comentarios;

    public Iniciativa(int id, int estado, int usuario_documento, String nombre, String descripcion, Date fechaCreacion, ArrayList<Interes> interasados,ArrayList<Comentario>comentarios,ArrayList<String> palabrasClave) {
        this.id = id;
        this.estado = estado;
        this.usuario_documento = usuario_documento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.palabrasClave = palabrasClave;
        this.interasados = interasados;
        this.comentarios = comentarios;
        
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
    

    public int getUsuario_documento() {
        return usuario_documento;
    }

    public void setUsuario_documento(int usuario_documento) {
        this.usuario_documento = usuario_documento;
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
    
    public ArrayList<Interes> getInterasados(){
    	return interasados;
    }
    public void setInterasados(ArrayList<Interes> interasados) {
    	this.interasados = interasados;
    }
    
    public ArrayList<Comentario> getComentarios(){
    	return comentarios;
    }
    public void setComentarios(ArrayList<Comentario> comentarios) {
    	this.comentarios = comentarios;
    }


    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", estado=" + estado + ", usuario_documento=" + usuario_documento + ", nombre=" + nombre + ",descripcion=" + descripcion + "fechaCreacion=" + descripcion + "descripcion=" + descripcion+"}";
    }


    
    
    
    
}
