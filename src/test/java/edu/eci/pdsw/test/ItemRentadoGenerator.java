package edu.eci.pdsw.test;

import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;

import java.util.Date;

import org.quicktheories.core.Gen;
import static org.quicktheories.generators.SourceDSL.*;

public class ItemRentadoGenerator {
    
    public static Gen<ItemRentado> itemsRentado() {
        return ids().zip(items(), fechasInicioRenta(), fechasFinRenta(),
                (id, item, fechainiciorenta, fechafinrenta) -> new ItemRentado(id, item, fechainiciorenta, fechafinrenta));
    }

    private static Gen<Integer> ids() {
        return integers().between(1, 1999999999);
    }
    
    private static Gen<Item> items() {
        return ItemGenerator.items();
    }
    
    private static Gen<Date> fechasInicioRenta() {
        return dates().withMilliseconds(0);
    }
    
    private static Gen<Date> fechasFinRenta() {
        return dates().withMilliseconds(0);
    }
}
