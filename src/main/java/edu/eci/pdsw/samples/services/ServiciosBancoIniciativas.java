package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Usuario;

public interface ServiciosBancoIniciativas {

	public abstract void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas;

}
