package com.dtte.portal.obiee.model;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Base class that has properties to connect to the DB through MyBatis.
 * @author llopezmalo
 *
 */
public class PORTALBI_ConnectionManager implements AutoCloseable {
	
	private Reader reader;
	private SqlSession session;
	private String configFile;
	
	/**
	 * Al crear la instancia de objeto ya se tiene inicializada la sesión de BD.
	 */
	public PORTALBI_ConnectionManager() {
		this("mybatis-config.xml");
	}
	
	/**
	 * Al crear la instancia de objeto ya se tiene inicializada la sesión de BD.
	 * @param configFile Nombre del archivo de configuración de MyBatis para generar la sesión.
	 */
	public PORTALBI_ConnectionManager(String configFile) {
		this.configFile = configFile;
		initialize(configFile);
	}
	
	/**
	 * Inicializa la sesión para acceder a BD.
	 */
	public void initialize(String configFile) {
		try {
			reader = Resources.getResourceAsReader(configFile);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);		
			session = sqlSessionFactory.openSession();
		} catch (IOException e) {
			System.err.println("Error al inicializar configuracion MyBatis - " + e.getMessage());
		}
	}
	
	/**
	 * Abre una sesión solamente si no existe una ya abierta.
	 * @throws IOException 
	 */
	public void openSession() throws IOException {
		if (reader != null || !reader.ready()) {
			initialize(configFile);
		}
	}

	@Override
	public void close() throws Exception {
		if (reader != null)
			reader.close();
		if (session != null)
			session.close();
	}
	
	public SqlSession getSession() {
		return session;
	}

}
