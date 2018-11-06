package edu.eci.pdsw.samples.entities;

import java.util.ArrayList;

public enum Rol {
	ADMINISTRADOR, PMO_ODI,PROPONENTE,USUARIO_DE_CONSULTA;
        
        public static ArrayList<String> arreglo(){
            ArrayList<String> roles = new ArrayList<String>();
            for( Rol s: Rol.values()){
                roles.add(s.toString());
            }
            return roles;
        }
}
