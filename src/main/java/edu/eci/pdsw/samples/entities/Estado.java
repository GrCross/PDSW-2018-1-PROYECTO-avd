package edu.eci.pdsw.samples.entities;

import java.util.ArrayList;

public enum Estado {
	En_Espera, En_Revision,Proyecto,Solucionado;
        
        public static ArrayList<String> arreglo(){
            ArrayList<String> estados = new ArrayList<String>();
            for( Estado s: Estado.values()){
                estados.add(s.toString());
            }
            return estados;
        }
}
