/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 */
public class Usuario implements Serializable{
    
    
    private long documento;
    private String nombre;
    private String area;
    private long telefono;
    private String correo;
    private Rol rol;
    private ArrayList<Interes> intereses; 
    
    public Usuario() {
    }
    
    public Usuario(long newDocumento) {
        this.documento = newDocumento;
    }

    public Usuario(String nombre, long documento, String area, long telefono, String correo, String rol, ArrayList<Interes> intereses) {   
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.area = area;
        this.correo = correo;
        this.rol = Rol.valueOf(rol);
        this.intereses = intereses;

    }
    
    public Usuario(String nombre, long documento, String area, long telefono, String correo, String rol) {   
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.area = area;
        this.correo = correo;
        this.rol = Rol.valueOf(rol);
        this.intereses = new ArrayList<Interes>();

    }

           
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area=area;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = Rol.valueOf(rol);;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
 

    public ArrayList<Interes> getIntereses() {
        return intereses;
    }

    public void setIntereses(ArrayList<Interes> intereses) {
        this.intereses = intereses;
    }
    
    public String getStringRol(){
        return rol.toString();
    }
    

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", documento=" + documento +", telefono=" + telefono +", area=" + area +", correo=" + correo +'}';
    }

   
    
    
}
