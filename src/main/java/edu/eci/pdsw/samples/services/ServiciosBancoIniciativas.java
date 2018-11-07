package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Usuario;
import java.util.List;

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
        public abstract List<Iniciativa> consultarIniciativas() throws Exception;
        
        /**
         * Actualiza el estado de una iniciativa
         * @param estado
         * @param id
         * @throws Exception
         */
        public abstract void updateEstadoIniciativa(int estado, int id)  throws Exception;
        
}
