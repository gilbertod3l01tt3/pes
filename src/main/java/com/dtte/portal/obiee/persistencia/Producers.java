package com.dtte.portal.obiee.persistencia;

import java.io.Reader;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

@ApplicationScoped
public class Producers {

  private static final Logger LOG = Logger.getLogger(Producers.class.getName());

  /**
   * Simple SqlSessionFactory provider. Required by MyBatis.
   */
  @ApplicationScoped
  @Produces
  @SessionFactoryProvider
  public SqlSessionFactory produce() throws Exception {
    Reader reader = Resources.getResourceAsReader("mybatis-config_1.xml");
    LOG.info("Creando el SqlSessionFactory");
    SqlSessionFactory manager = new SqlSessionFactoryBuilder().build(reader);
    reader.close();
    return manager;
  }

 
}
