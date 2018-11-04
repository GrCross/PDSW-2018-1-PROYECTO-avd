package edu.eci.pdsw.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.eci.pdsw.sampleprj.dao.UsuarioDao;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBatisUsarioDao;
import edu.eci.pdsw.samples.services.ServiciosBancoIniciativas;
import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasIMPL;


public class GuiceContextListener implements ServletContextListener {

   
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");

                // TODO Add service class associated to Stub implementation
                
                bind(ServiciosBancoIniciativas.class).to(ServiciosBancoIniciativasIMPL.class);
                bind(UsuarioDao.class).to(MyBatisUsarioDao.class);
               
            }
        });

        servletContextEvent.getServletContext().setAttribute(Injector.class.getName(), injector);
    }
}
