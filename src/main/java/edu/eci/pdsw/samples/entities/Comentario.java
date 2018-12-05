/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 2106913
 */
public class Comentario implements Serializable {
    

    private String contenido;
    private Date fechaDePublicacion;
    private Usuario usuario;
    private long commentId;

    public Comentario(Usuario usuario, Date fechaDeCreacion, String contenido, long commentId) {
        this.usuario = usuario;
        this.fechaDePublicacion = fechaDeCreacion;
        this.contenido = contenido;
        this.commentId= commentId;
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

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }
    
    public Date getFechaDePublicacion() {
    	return fechaDePublicacion;
    }

    public String getFechaDePublicacion2() {
    	return new SimpleDateFormat("dd-MM-yyyy").format(fechaDePublicacion);
    }

    public void setFechaDePublicacion(Date fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Comentario{"+"Usuario: "+ usuario+", fechaDePublicacion=" + fechaDePublicacion + ", contenido=" + contenido + '}' + commentId;
    }

    
    
}
