/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author 2106913
 */
public class Comentario implements Serializable {

    

    private String contenido;
    private Date fechaDeCreacion;
    private int Usuario_documento;
    private int iniciativa_id;   

    public Comentario(int Usuario_documento, int iniciativa_id, Date fechaDeCreacion, String contenido) {
        this.Usuario_documento = Usuario_documento;
        this.iniciativa_id = iniciativa_id;
        this.fechaDeCreacion = fechaDeCreacion;
        this.contenido = contenido;
    }

    public Comentario() {
    }

    public int getUsuarioDocumento() {
        return Usuario_documento;
    }

    public void setUsuarioDocumento(int Usuario_documento) {
        this.Usuario_documento = Usuario_documento;
    }

    public int getIniciativaId() {
        return iniciativa_id;
    }

    public void setIniciativaId(int iniciativa_id) {
        this.iniciativa_id = iniciativa_id;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(Date fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setCotenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Comentario{" + "Usuario_documento=" + Usuario_documento + ", iniciativa_id=" + iniciativa_id + ", fechaDeCreacion=" + fechaDeCreacion + ", contenido=" + contenido + '}';
    }

    
    
}
