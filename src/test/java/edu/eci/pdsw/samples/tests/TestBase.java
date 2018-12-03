package edu.eci.pdsw.samples.tests;

import org.junit.Before;
import org.mybatis.guice.XMLMyBatisModule;

import edu.eci.pdsw.sampleprj.dao.ComentarioDAO;
import edu.eci.pdsw.sampleprj.dao.IniciativaDAO;
import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBatisComentarioDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBatisIniciativaDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBatisUsuarioDao;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasIMPL;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.pdsw.sampleprj.dao.InteresDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBatisInteresDAO;


public class TestBase {
    
    protected Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId("test");
                setClassPathResource("mybatis-config-h2.xml");
                
                bind(ServiciosBancoIniciativas.class).to(ServiciosBancoIniciativasIMPL.class);
                bind(UsuarioDao.class).to(MyBatisUsuarioDao.class);
                bind(IniciativaDAO.class).to(MyBatisIniciativaDAO.class);
                bind(ComentarioDAO.class).to(MyBatisComentarioDAO.class);
                bind(InteresDAO.class).to(MyBatisInteresDAO.class);
            }
    });
    
    @Before
    public void setup() {
        injector.injectMembers(this);
    }
}