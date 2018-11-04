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
    
    
    private int documento;
    private String nombre;
    private String area;
    private String telefono;
    private String correo;
    private Rol rol;
    private ArrayList<Interes> intereses; 
    private ArrayList<Iniciativa> iniciativas;
    private ArrayList<Comentario> comentarios;

    public Usuario() {
    }

    public Usuario(String nombre, int documento, String area, String telefono, String correo, String rol, ArrayList<Interes> intereses,ArrayList<Iniciativa> iniciativas,ArrayList<Comentario> comentarios) {   
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.area = area;
        this.correo = correo;
        this.rol = Rol.valueOf(rol);
        this.intereses = intereses;
        this.iniciativas = iniciativas;
        this.comentarios = comentarios;
    }
    
    public Usuario(String nombre, int documento, String area, String telefono, String correo, String rol) {   
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.area = area;
        this.correo = correo;
        this.rol = Rol.valueOf(rol);
        this.intereses = new ArrayList<Interes>();
        this.iniciativas = new ArrayList<Iniciativa>();
        this.comentarios = new ArrayList<Comentario>();
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

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
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
    
    public ArrayList<Iniciativa> getIniciativas() {
        return this.iniciativas;
    }

    public void setIniciativas(ArrayList<Iniciativa> iniciativas) {
        this.iniciativas = iniciativas;
    }
    
    public ArrayList<Comentario> getComentarios() {
        return this.comentarios;
    }

    public void setcomentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", documento=" + documento +", telefono=" + telefono +", area=" + area +", correo=" + correo +'}';
    }

   
    
    
}
