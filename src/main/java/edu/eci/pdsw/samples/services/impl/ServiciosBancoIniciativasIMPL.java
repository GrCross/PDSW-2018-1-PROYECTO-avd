package edu.eci.pdsw.samples.services.impl;

import org.apache.ibatis.exceptions.PersistenceException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import edu.eci.pdsw.sampleprj.dao.IniciativaDAO;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;

import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;

@Singleton
public class ServiciosBancoIniciativasIMPL implements ServiciosBancoIniciativas {
	
    @Inject
    private UsuarioDao usuarioDao;
    
    @Inject
    private IniciativaDAO iniciativaDAO;

    public ServiciosBancoIniciativasIMPL() {
		// TODO Auto-generated constructor stub
    }

    @Override
    public void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas {
        try {
            usuarioDao.save(usuario);
        } catch (PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al registrar el usuario ", e);
        }
	
     }

    @Override
    public void InsertarIniciativa(Iniciativa iniciativa) throws ExcepcionBancoIniciativas {
        try {
            iniciativaDAO.save(iniciativa);
        } catch (PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al registrar la iniciativa ", e);
        }
    }

	@Override
	public boolean autorizacionLogin(String user) throws ExcepcionBancoIniciativas {
		boolean respuesta=false;
		try{
			System.out.println("___________________________user____________"+user);
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			System.out.println("tttttttttttttttttttttttttttttttt"+usuarioDao.getRolusuario(user));
			int y=usuarioDao.compararUsuario(user);
			System.out.println(y+"zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
			if(y==1) {
				respuesta=true;
				return respuesta;
			}
			else {respuesta=false;
					return respuesta;}
		}
		catch (PersistenceException e) {
            throw new ExcepcionBancoIniciativas("Error al realizar la comprobacion del login ", e);
        }
		
	}
	
	
	@Override
	public Rol tipoUsuario(String correo) throws ExcepcionBancoIniciativas{
		return Rol.valueOf(usuarioDao.getRolusuario(correo));
	}

}
