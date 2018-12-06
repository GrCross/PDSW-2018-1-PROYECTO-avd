package edu.eci.pdsw.samples.services;

import com.google.inject.Injector;
import edu.eci.pdsw.sampleprj.dao.UsuarioDao;

import edu.eci.pdsw.sampleprj.dao.mybatis.MyBatisUsuarioDao;
import edu.eci.pdsw.samples.services.impl.ServiciosBancoIniciativasIMPL;

import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.XMLMyBatisModule;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;
import edu.eci.pdsw.sampleprj.dao.ComentarioDAO;
import edu.eci.pdsw.sampleprj.dao.mybatis.MyBatisComentarioDAO;

public class ServiciosBancoIniciativasFactory {

	

	private static ServiciosBancoIniciativasFactory instance = new ServiciosBancoIniciativasFactory();

   private static Optional<Injector> optInjector;

   private Injector myBatisInjector(String env, String pathResource) {
       return createInjector(new XMLMyBatisModule() {
           @Override
           protected void initialize() {
               setEnvironmentId(env);
               setClassPathResource(pathResource);
               bind(UsuarioDao.class).to(MyBatisUsuarioDao.class);
               bind(ComentarioDAO.class).to(MyBatisComentarioDAO.class);
               bind(ServiciosBancoIniciativas.class).to(ServiciosBancoIniciativasIMPL.class);
               
              
           }
       });
   }

   private ServiciosBancoIniciativasFactory(){
       optInjector = Optional.empty();
   }

   public ServiciosBancoIniciativas getServiciosAlquiler(){
       if (!optInjector.isPresent()) {
           optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
       }

       return optInjector.get().getInstance(ServiciosBancoIniciativas.class);
   }

   public static ServiciosBancoIniciativasFactory getInstance(){
       return instance;
   }
}