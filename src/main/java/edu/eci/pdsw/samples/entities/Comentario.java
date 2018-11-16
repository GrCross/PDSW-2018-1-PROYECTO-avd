/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 2106913
 */
public class Comentario implements Serializable {
    

    private String contenido;
    private Date fechaDePublicacion;
    private Usuario usuario;

    public Comentario(Usuario usuario, Date fechaDeCreacion, String contenido) {
        this.usuario = usuario;
        this.fechaDePublicacion = fechaDeCreacion;
        this.contenido = contenido;
    }

    public Comentario() {
    }

    public Usuario getUsuarioDocumento() {
        return usuario;
    }

    public void setUsuarioDocumento(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaDeCreacion() {
        return fechaDePublicacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDePublicacion = fechaDeCreacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setCotenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Comentario{"+"Usuario: "+ usuario+", fechaDePublicacion=" + fechaDePublicacion + ", contenido=" + contenido + '}';
    }

    
    
}
