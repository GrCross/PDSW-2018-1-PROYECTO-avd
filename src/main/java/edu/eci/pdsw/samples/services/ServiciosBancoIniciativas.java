package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;

public interface ServiciosBancoIniciativas {

	public abstract void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas;

    public abstract void InsertarIniciativa(Iniciativa iniciativa) throws ExcepcionBancoIniciativas;
    
    public abstract boolean autorizacionLogin(String user) throws ExcepcionBancoIniciativas;

    public abstract Rol tipoUsuario(String correo) throws ExcepcionBancoIniciativas;
    
}
