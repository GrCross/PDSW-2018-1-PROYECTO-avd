package edu.eci.pdsw.test;

import org.junit.Before;
import org.mybatis.guice.XMLMyBatisModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.eci.pdsw.sampleprj.dao.ClienteDAO;
import edu.eci.pdsw.sampleprj.dao.ItemDAO;
import edu.eci.pdsw.sampleprj.dao.ItemRentadoDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISClienteDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISItemDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBATISItemRentadoDAO;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.impl.ServiciosAlquilerImpl;

public class TestBase {
    
    protected Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId("test");
                setClassPathResource("mybatis-config-h2.xml");
                
                bind(ServiciosAlquiler.class).to(ServiciosAlquilerImpl.class);
                bind(ClienteDAO.class).to(MyBATISClienteDAO.class);
                bind(ItemDAO.class).to(MyBATISItemDAO.class);
                bind(ItemRentadoDAO.class).to(MyBATISItemRentadoDAO.class);
            }
    });
    
    @Before
    public void setup() {
        injector.injectMembers(this);
    }
}