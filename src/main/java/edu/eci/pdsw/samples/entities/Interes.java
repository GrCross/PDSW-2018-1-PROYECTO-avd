/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;

/**
 *
 * @author 2106913
 */
public class Interes implements Serializable{
    private int iniciativa_documento;
    private int usuario_id;
    private int afinidad;
    private boolean voto;

    public Interes(int iniciativa_documento, int usuario_id, int afinidad, boolean voto ) {
        this.iniciativa_documento = iniciativa_documento;
        this.usuario_id =usuario_id;
        this.afinidad =afinidad;
        this.voto = voto;
    }

    public Interes() { 
    	
    }    

    public int getIniciativa_Documento() {
        return iniciativa_documento;
    }

    public void setIniciativa_Documento(int iniciativa_documento) {
        this.iniciativa_documento = iniciativa_documento;
    }   

    public int getAfinidad() {
        return afinidad;
    }

    public void setAfinidad(int afinidad) {
        this.afinidad = afinidad;
    }
    
    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    
    public boolean getVoto() {
        return voto;
    }

    public void setVoto(boolean  voto) {
        this.voto = voto;
    }
    
    

    @Override
    public String toString() {
        return "Interes{" + "iniciativa_documento=" + iniciativa_documento + ", afinidad=" + afinidad + ", usuario_id=" + usuario_id +", voto=" + voto +'}';
    }
    
    
}
