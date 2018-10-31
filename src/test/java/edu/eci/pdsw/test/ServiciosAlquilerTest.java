package edu.eci.pdsw.test;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

public class ServiciosAlquilerTest extends TestBase {

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    /**
     *
     */
    @Before
    public void setUp() {
        //Al inicio
    }

    @Test
    public void emptyDB() {
        qt().forAll(longs().from(1).upTo(1000)).check((documento) -> {
            boolean r = true;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(documento);
                if (cliente.getDocumento() == documento) {
                    r = false;
                }
            } catch (ExcepcionServiciosAlquiler | IndexOutOfBoundsException | NullPointerException e) {
                r = true;
            }
            return r;
        });
    }

    @Test
    public void deberiaRegistrarYConsultarClientes() {
        qt().forAll(ClienteGenerator.clientes()).check(cliente -> {
            boolean clienteRegistrado = true;
            Optional<Cliente> c = Optional.empty();
            try {
                serviciosAlquiler.registrarCliente(cliente);
                c = Optional.of(serviciosAlquiler.consultarCliente(cliente.getDocumento()));
            } catch (ExcepcionServiciosAlquiler ex) {
                System.out.println(ex.getMessage());
                clienteRegistrado = false;
            }
            if (!c.isPresent()) {
                clienteRegistrado = false;
            }
            try {
                serviciosAlquiler.eliminarCliente(cliente);
            } catch (ExcepcionServiciosAlquiler ex) {
                System.out.println(ex.getMessage());
            }
            return clienteRegistrado;
        });
    }

    @Test
    public void deberiaRegistrarYConsultarItems() {
        qt().forAll(ItemGenerator.items()).check(item -> {
            boolean itemRegistrado = true;
            Optional<Item> i = Optional.empty();
            try {
                serviciosAlquiler.registrarItem(item);
                i = Optional.of(serviciosAlquiler.consultarItem(item.getId()));
            } catch (ExcepcionServiciosAlquiler ex) {
                System.out.println(ex.getMessage());
                itemRegistrado = false;
            }
            if (!i.isPresent()) {
                itemRegistrado = false;
            }
            try {
                serviciosAlquiler.eliminarItem(item);
            } catch (ExcepcionServiciosAlquiler ex) {
                System.out.println(ex.getMessage());
            }
            return itemRegistrado;
        });
    }

    @Test
    public void servicioRegistrarAlquiler() {
        qt()
                .forAll(ClienteGenerator.clientes(), ItemGenerator.items(), dates().withMilliseconds(0), integers().from(1).upTo(6))
                .check((cliente, item, fecha, numDias) -> {
                    boolean itemsAlquilados = true;
                    try {
                        serviciosAlquiler.registrarCliente(cliente);
                        serviciosAlquiler.registrarItem(item);
                        serviciosAlquiler.registrarAlquilerCliente(fecha, cliente.getDocumento(), item, numDias);
                        Cliente c = serviciosAlquiler.consultarCliente(cliente.getDocumento());
                        List<ItemRentado> itemsCliente = serviciosAlquiler.consultarItemsCliente(c.getDocumento());
                        if (itemsCliente.isEmpty()) {
                            itemsAlquilados = false;
                        }
                    } catch (ExcepcionServiciosAlquiler ex) {
                        System.out.println(ex.getMessage());
                        itemsAlquilados = false;
                    }
                    try {
                        serviciosAlquiler.eliminarCliente(cliente);
                        serviciosAlquiler.eliminarItem(item);
                        serviciosAlquiler.eliminarAlquiler(item);
                    } catch (ExcepcionServiciosAlquiler ex) {
                        System.out.println(ex.getMessage());
                    }
                    return itemsAlquilados;
                });
    }

    @Test
    public void servicioActualizarTarifa() {
        qt()
                .forAll(ItemGenerator.items(), longs().from(495001).upToAndIncluding(500000))
                .check((item, tarifa) -> {
                    boolean cambioTarifa = true;
                    long tarifaAntigua = item.getTarifaxDia();
                    long tarifaNueva = 0;
                    try {
                        serviciosAlquiler.registrarItem(item);
                        serviciosAlquiler.actualizarTarifaItem(item.getId(), tarifa);
                        Item i = serviciosAlquiler.consultarItem(item.getId());
                        tarifaNueva = i.getTarifaxDia();
                        if (tarifaAntigua == tarifaNueva) {
                            cambioTarifa = false;
                        }
                    } catch (ExcepcionServiciosAlquiler ex) {
                        System.out.println(ex.getMessage());
                        cambioTarifa = false;
                    }
                    try {
                        serviciosAlquiler.eliminarItem(item);
                    } catch (ExcepcionServiciosAlquiler ex) {
                        System.out.println(ex.getMessage());
                    }
                    return cambioTarifa;
                });
    }
    /*
    @Test
    public void servicioVetarCliente() {
        qt()
            .forAll(ClienteGenerator.clientes())
            .check((cliente) -> {
                boolean estaVetado = true;
                try {
                    serviciosAlquiler.registrarCliente(cliente);
                    Cliente c = serviciosAlquiler.consultarCliente(cliente.getDocumento());
                    serviciosAlquiler.vetarCliente(c.getDocumento(), true);
                    if (!c.isVetado()) {
                        estaVetado = false;
                    }
                } catch (ExcepcionServiciosAlquiler ex) {
                    System.out.println(ex.getMessage());
                    estaVetado = false;
                }
                serviciosAlquiler.eliminarCliente(cliente);
                return estaVetado;
            });
    }*/

}
