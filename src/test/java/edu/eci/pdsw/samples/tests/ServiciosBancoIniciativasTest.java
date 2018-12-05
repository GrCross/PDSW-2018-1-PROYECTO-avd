package edu.eci.pdsw.samples.tests;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Interes;
import edu.eci.pdsw.samples.entities.Rol;


import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.tests.generators.IniciativasGenerator;
import edu.eci.pdsw.samples.tests.generators.UsuariosGenerator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

public class ServiciosBancoIniciativasTest extends TestBase {
    int n = 801111;
    @Inject
    private ServiciosBancoIniciativas serviciosAlquiler;

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
                Usuario cliente = serviciosAlquiler.consultarUsuario(documento);
            } catch(ExcepcionBancoIniciativas e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            return r;
        });
    }
    
    
    @Test
    public void consultarIniciativas() throws ExcepcionBancoIniciativas{
        Usuario u = new Usuario("Prueba", 1, "aarea1", 11111, "prueba@correo.com", Rol.PROPONENTE);
        serviciosAlquiler.InsertarUsuario(u);
        qt().forAll(IniciativasGenerator.iniciativas(u)).check(iniciativa ->{
            boolean prueba = false;
            try {
                n+=1;
                iniciativa.setId(n);
                serviciosAlquiler.agregarIniciativaTest(iniciativa);
                Iniciativa ini = serviciosAlquiler.consultarIniciativa(iniciativa.getId());
                if(ini.getId() == n){
                    prueba = true;
                    
                }
            } catch (Exception ex) {
                
                Logger.getLogger(ServiciosBancoIniciativasTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prueba;
        });
    }
    
    /*@Test
    public void agregarUsuarios() throws ExcepcionBancoIniciativas{
        Usuario u = new Usuario("Prueba", 1, "aarea1", 11111, "prueba@correo.com", Rol.PROPONENTE);
        Usuario u2 = new Usuario("Prueba", 2, "aarea1", 11111, "prueba@correo.com", Rol.PROPONENTE);
        serviciosAlquiler.InsertarUsuario(u);
        serviciosAlquiler.InsertarUsuario(u2);
        
        qt().forAll(IniciativasGenerator.iniciativas(u)).check(iniciativa ->{
            boolean prueba = false;
            try {
                n+=1;
                Interes interesPrueba = new Interes(iniciativa.getId(), 45, true);
                serviciosAlquiler.agregarInteres(iniciativa.getId(), u2.getDocumento(), interesPrueba);
                Interes interesObtenido = serviciosAlquiler.consultarInteres(iniciativa.getId(), u2.getDocumento());
                //if(interesObtenido.getIniciativa().getId() == interesPrueba.getIniciativa()){
                
                //}
                
                prueba = true;
                
            } catch (Exception ex) {
                
                Logger.getLogger(ServiciosBancoIniciativasTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prueba;
        });
    }
    
   /* @Test
    public void SeraPosibleVotaryConsultarLosVotados() throws ExcepcionBancoIniciativas{
        qt().forAll(UsuariosGenerator.Usuarios()).check(usuario ->{
            boolean prueba = false;
            try {
                n+=1;
                usuario.setDocumento(n);
                serviciosAlquiler.InsertarUsuario(usuario);
                Usuario uTest = serviciosAlquiler.consultarUsuario(usuario.getDocumento());
                if(usuario.getDocumento() == uTest.getDocumento()){
                    prueba = true;
                }
            } catch (Exception ex) {
                
                Logger.getLogger(ServiciosBancoIniciativasTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prueba;
        });
    }*/


    

}
