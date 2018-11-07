package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ServiciosBancoIniciativas {

	public abstract void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas;

        public abstract void InsertarIniciativa(Iniciativa iniciativa) throws Exception;
        
        public abstract Usuario consultarUsuario (long documento) throws ExcepcionBancoIniciativas;
        
        public abstract void updateUsuario(long documento,String rol) throws Exception;
        
        public abstract List<Usuario> consultarUsuarios() throws Exception;
        
        /**
         * Lista de todas las iniciativas
         * @return
         * @throws Exception
         */
        public abstract List<Iniciativa> consultarIniciativasEst() throws Exception;
        
        /**
         * Actualiza el estado de una iniciativa
         * @param estado
         * @param id
         * @throws Exception
         */
        public abstract void updateEstadoIniciativa(int estado, int id)  throws Exception;
        
        public ArrayList<Iniciativa> consultarIniciativas() throws PersistenceException;
        
        public abstract boolean autorizacionLogin(String user) throws ExcepcionBancoIniciativas;
        
        public abstract Rol getRolUsuario(String correo) throws ExcepcionBancoIniciativas;
        
}
