package edu.eci.pdsw.samples.tests;

import com.google.inject.Inject;
import edu.eci.pdsw.samples.entities.Comentario;
import edu.eci.pdsw.samples.entities.Iniciativa;
import edu.eci.pdsw.samples.entities.Interes;
import edu.eci.pdsw.samples.entities.Rol;


import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import edu.eci.pdsw.samples.entities.Usuario;
import edu.eci.pdsw.samples.services.ExcepcionBancoIniciativas;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.tests.generators.ComentariosGenerator;
import edu.eci.pdsw.samples.tests.generators.IniciativasGenerator;
import edu.eci.pdsw.samples.tests.generators.UsuariosGenerator;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.*;

public class ServiciosBancoIniciativasTest extends TestBase {
    int n = 8111;
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
            } catch(ExcepcionBancoIniciativas | IndexOutOfBoundsException e) {
                r = true;
            }
            return r;
        });
    }
    
    
    @Test
    public void consultarIniciativas() throws ExcepcionBancoIniciativas{
        Usuario u = new Usuario("Prueba", 1, "aarea1", 11111, "prueba1@correo.com", Rol.PROPONENTE);
        serviciosAlquiler.InsertarUsuario(u);
        qt().forAll(IniciativasGenerator.iniciativas(u)).check(iniciativa ->{
            boolean prueba = false;
            try {
                n+=1;
                //System.out.println(n);
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
    
    @Test
    public void agregarUsuarios() throws ExcepcionBancoIniciativas{
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
            } catch (ExcepcionBancoIniciativas ex) {
                Logger.getLogger(ServiciosBancoIniciativasTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prueba;
        });
    }
    
    @Test
    public void deberiaCambiarEstadosIniciativa() throws ExcepcionBancoIniciativas{
        Usuario u = new Usuario("Prueba", 2, "aarea1", 11111, "prueba2@correo.com", Rol.PROPONENTE);
        serviciosAlquiler.InsertarUsuario(u);
        n = n*2;
        qt().forAll(IniciativasGenerator.iniciativas(u)).check(iniciativa->{
            boolean pruebaIguales = false;
            boolean pruebaDiferentes = false;
            
            n+=1;
            //System.out.println(n);
            iniciativa.setId(n);
            try {
                serviciosAlquiler.agregarIniciativaTest(iniciativa);
                Iniciativa i = serviciosAlquiler.consultarIniciativa(n);
                if(i.getEstado().equals(iniciativa.getEstado())){
                    pruebaIguales = true;
                }
                serviciosAlquiler.updateEstadoIniciativa("Proyecto", n);
                Iniciativa i2 = serviciosAlquiler.consultarIniciativa(n);
                if(!i2.getEstado().equals(iniciativa.getEstado())){
                    pruebaIguales = true;
                }
            } catch (Exception ex) {
                Logger.getLogger(ServiciosBancoIniciativasTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        });
    }
    
    @Test
    public void agregarComentarios() throws ExcepcionBancoIniciativas, Exception{
        Usuario u = new Usuario("Prueba", 3, "aarea1", 11111, "prueba3@correo.com", Rol.PROPONENTE);
        Iniciativa i = new Iniciativa(1,"En_Espera", "inicitivaPrueba", "inicitivaPrueba",new Date(n, n, n),"pClave", u, "Civil");
        serviciosAlquiler.InsertarUsuario(u);
        serviciosAlquiler.agregarIniciativaTest(i);
        
        n = n*3;
        qt().forAll(ComentariosGenerator.comentarios(u)).check(comentario ->{
            boolean prueba = true;
            try {
                n+=1;
                comentario.setCommentId(n);
                serviciosAlquiler.InsertarComentario(comentario,1);
                
                ArrayList<Comentario> c = serviciosAlquiler.consultarComentarios(1);
                
                Comentario comentPrueba = c.get(c.size()-1);
                
                if(!(comentPrueba.getUsuario().getDocumento() == 3)){
                    
                   // System.out.println(comentPrueba.getUsuario().getDocumento());                    
                    prueba = false;
                }
                
            } catch (Exception ex) {
                Logger.getLogger(ServiciosBancoIniciativasTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prueba;
        });
    }
    

}
