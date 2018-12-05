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
    private int afinidad;
    private boolean voto;
    private Iniciativa iniciativa;

    public Interes(int iniciativa_documento, int afinidad, boolean voto) {
        this.afinidad =afinidad;
        this.voto = voto;
    }

    public Interes() { 
    	
    }    


    public int getAfinidad() {
        return afinidad;
    }

    public void setAfinidad(int afinidad) {
        this.afinidad = afinidad;
    }   
    
    
    public boolean getVoto() {
        return voto;
    }

    public void setVoto(boolean  voto) {
        this.voto = voto;
    }

    public Iniciativa getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(Iniciativa iniciativa) {
        this.iniciativa = iniciativa;
    }
    

    @Override
    public String toString() {
        return "Interes{" + ", afinidad=" + afinidad + ", voto=" + voto +'}';
    }
    
    
}
