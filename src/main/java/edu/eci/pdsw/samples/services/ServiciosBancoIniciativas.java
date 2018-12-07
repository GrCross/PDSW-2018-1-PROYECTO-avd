package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Interes;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.exceptions.PersistenceException;

public interface ServiciosBancoIniciativas {

	public abstract void InsertarUsuario(Usuario usuario) throws ExcepcionBancoIniciativas;

        public abstract void InsertarIniciativa(Iniciativa iniciativa) throws ExcepcionBancoIniciativas;
        
        public abstract Usuario consultarUsuario (long documento) throws ExcepcionBancoIniciativas;
        
        public abstract void updateUsuario(long documento,String rol) throws ExcepcionBancoIniciativas;
        
        public abstract List<Usuario> consultarUsuarios() throws ExcepcionBancoIniciativas;
        
        /**
         * Lista de todas las iniciativas
         * @return
         * @throws Exception
         */
        public abstract List<Iniciativa> consultarIniciativasEst() throws ExcepcionBancoIniciativas;
        
        /**
         * Actualiza el estado de una iniciativa
         * @param estado
         * @param id
         * @throws Exception
         */
        public abstract void updateEstadoIniciativa(String estado, int id)  throws ExcepcionBancoIniciativas;
        
        public abstract ArrayList<Iniciativa> consultarIniciativas() throws ExcepcionBancoIniciativas;
        
        public abstract boolean autorizacionLogin(String user, String psw) throws ExcepcionBancoIniciativas;
        
        public abstract Usuario obtenerUsuario(String user)throws ExcepcionBancoIniciativas;
        
        public abstract void InsertarComentario (Comentario com, int idIni) throws ExcepcionBancoIniciativas;
        
        public abstract ArrayList<Comentario> consultarComentarios(int id) throws ExcepcionBancoIniciativas;
        
        public abstract long consultarMax() throws ExcepcionBancoIniciativas;
        
        public abstract List<Iniciativa> iniciativasUnUsuario (long documento) throws ExcepcionBancoIniciativas;
        
        //public abstract void cambiarInformacionIniciativa(String descripcion, String palabrasclave, int id) throws Exception;
        public abstract void cambiarDescripcionIniciativa(String descripcion, int id) throws ExcepcionBancoIniciativas;
        
        public abstract void cambiarNombreIniciativa(String nombre, int id)throws ExcepcionBancoIniciativas;

        public abstract Iniciativa consultarIniciativa(int id) throws ExcepcionBancoIniciativas;
        
        public abstract Interes consultarInteres( int documento,long usuario ) throws ExcepcionBancoIniciativas;
        
        public abstract void updateInteres (int documento,long usuario, boolean like  )throws ExcepcionBancoIniciativas;
        
        public abstract void agregarInteres (int documento,long usuario,Interes interes) throws ExcepcionBancoIniciativas;
        

        public abstract Iniciativa LikesUnaIniciativa (int id) throws ExcepcionBancoIniciativas;


        public abstract void agregarIniciativaTest(Iniciativa ini)throws ExcepcionBancoIniciativas;

       


        
}
