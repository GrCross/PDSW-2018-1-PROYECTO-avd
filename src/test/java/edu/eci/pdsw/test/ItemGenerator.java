package edu.eci.pdsw.test;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.TipoItem;
import java.util.Date;

import org.quicktheories.core.Gen;
import static org.quicktheories.generators.SourceDSL.*;

public class ItemGenerator {

    public static Gen<Item> items() {
        return ids().zip(tipos(), nombres(), fechasLanzamiento(), tarifasxdia(),
                (id, tipo, nombre, fechaLanzamiento, tarifaxDia) -> new Item(tipo, id, nombre, "", fechaLanzamiento, tarifaxDia, "", ""));
    }

    private static Gen<TipoItem> tipos() {
        return TipoItemGenerator.tiposItems();
    }

    private static Gen<Integer> ids() {
        return integers().between(1, 199999999);
    }

    private static Gen<String> nombres() {
        return strings().basicLatinAlphabet().ofLengthBetween(5, 45);
    }

    private static Gen<Date> fechasLanzamiento() {
        return dates().withMilliseconds(0);
    }

    private static Gen<Long> tarifasxdia() {
        return longs().between(1000, 495000);
    }

}
