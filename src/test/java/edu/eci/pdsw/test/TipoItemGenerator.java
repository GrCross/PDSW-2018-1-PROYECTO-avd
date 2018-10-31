package edu.eci.pdsw.test;

import edu.eci.pdsw.samples.entities.TipoItem;

import org.quicktheories.core.Gen;
import static org.quicktheories.generators.SourceDSL.*;

public class TipoItemGenerator {
    
    public static Gen<TipoItem> tiposItems(){
        return ids().zip(descripciones(), (id, descripcion) -> new TipoItem(id, descripcion));
    }
    
    private static Gen<Integer> ids(){
        return integers().between(1, 999);
    }
    
    private static Gen<String> descripciones(){
        return strings().basicLatinAlphabet().ofLengthBetween(10, 45);
    }
    
}