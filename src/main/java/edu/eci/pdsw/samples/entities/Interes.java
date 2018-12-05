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
    
    private boolean voto;
    private Iniciativa iniciativa;

    public Interes(boolean voto ) {

        
        this.voto = voto;
    }
    
    public Interes(Iniciativa iniciativa,boolean voto) {
        this.iniciativa = iniciativa;
        this.voto = voto;
    }
    

    public Interes() { 
    	
    }
    
    
    public boolean getVoto() {
        return voto;
    }

    public void setVoto(boolean  voto) {
        this.voto = voto;
    }
    
    

    @Override
    public String toString() {
        return "Interes{" + ", afinidad="  + ", voto=" + voto +'}';
    }
    
    
}
