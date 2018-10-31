package edu.eci.pdsw.test;

import edu.eci.pdsw.samples.entities.Cliente;

import org.quicktheories.core.Gen;
import static org.quicktheories.generators.SourceDSL.*;

public class ClienteGenerator {
    
    public static Gen<Cliente> clientes(){
        return documentos().zip(nombres(), telefonos(), direcciones(), emails(),
            (documento, nombre, telefono, direccion, email) -> new Cliente(nombre, documento, telefono, direccion, email));
    }
    
    private static Gen<String> nombres(){
        return strings().basicLatinAlphabet().ofLengthBetween(5, 35);
    }
    
    private static Gen<Long> documentos(){
        return longs().between(10000000, 1999999999);
    }
    
    private static Gen<String> telefonos(){
        return strings().numericBetween(1000000, 1999999999);
    }
    
    private static Gen<String> direcciones(){
        return strings().basicLatinAlphabet().ofLengthBetween(10, 145);
    }
    
    private static Gen<String> emails(){
        return strings().basicLatinAlphabet().ofLengthBetween(10, 145);
    }
    
}
