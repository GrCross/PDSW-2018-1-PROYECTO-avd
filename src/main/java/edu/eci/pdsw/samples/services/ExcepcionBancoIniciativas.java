package edu.eci.pdsw.samples.services;

import org.apache.ibatis.exceptions.PersistenceException;

public class ExcepcionBancoIniciativas extends Exception {

private static final long serialVersionUID = 1L;
	
	public static final String  EXCEPCIONES = "";
    
    public ExcepcionBancoIniciativas(String message){
        super(message);
    }
    
    public ExcepcionBancoIniciativas(String message, PersistenceException ex){
        super(message);
    } 
	

}
