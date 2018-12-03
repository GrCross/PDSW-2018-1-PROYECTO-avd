package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.Usuario;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.generators.SourceDSL.*;

public class UsuariosGenerator {
    
    public static Gen<Usuario> Usuarios(){
        return documentos().zip(nombres(),correo(),rol(),
            (documento, nombre, correo, rol) -> new Usuario(nombre, documento, "Civil", 11111, correo, rol));
    }
    
    private static Gen<String> nombres(){
        return strings().basicLatinAlphabet().ofLengthBetween(5, 35);
    }
    
    private static Gen<Long> documentos(){
        return longs().between(1, 1999999999);
    }

    private static Gen<String> area(){
        return strings().basicLatinAlphabet().ofLengthBetween(10, 145);
    }
    
    private static Gen<String> telefonos(){
        return strings().numericBetween(1000000, 1999999999);
    }
    
    private static Gen<String> correo(){
        return strings().basicLatinAlphabet().ofLengthBetween(5, 50);
    }

    public static Gen<Rol> rol() {
        Class d = Rol.class;
        return Generate.enumValues(d);
    }
}
