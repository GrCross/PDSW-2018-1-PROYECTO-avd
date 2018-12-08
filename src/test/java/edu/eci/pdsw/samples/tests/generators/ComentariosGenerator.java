package edu.eci.pdsw.samples.tests.generators;

import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.Estado;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.generators.SourceDSL.*;

import java.util.Date;

public class ComentariosGenerator {
    
    public static Gen<Comentario> comentarios(Usuario u){
        return commentId().zip(contenido(),fechaDePublicacion(),
            (commentId,contenido,fechaDeCreacion) -> new Comentario(u, fechaDeCreacion, contenido, commentId));
    }
    
    private static Gen<Long> commentId(){
        return longs().between(10000000, 1999999999);
    }  
   
    private static Gen<String> contenido(){
        return strings().basicLatinAlphabet().ofLengthBetween(5, 35);
    }
    private static Gen<Date> fechaDePublicacion(){
        return dates().withMilliseconds(0);
    }
    
    private static Gen<String> palabrasClave(){
        return strings().basicLatinAlphabet().ofLengthBetween(10, 145);
    }

    private static Gen<Usuario> usuario(){
        return UsuariosGenerator.Usuarios();
    }
    
    
    
       
}
