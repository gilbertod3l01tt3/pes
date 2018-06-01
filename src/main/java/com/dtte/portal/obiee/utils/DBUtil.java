package com.dtte.portal.obiee.utils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	private static DataSource dataSource;
	private static final String  JNDI_LOOKUP_SERVICE = "sepTambor";
	
	static {
		initializeDataSource();
	}
	
	public static DataSource getDataSource() {
		if (dataSource == null) {
			initializeDataSource();
		}
		return dataSource;
	}
	
	private static void initializeDataSource() {
		try {
			Context context = new InitialContext();
			Object lookup = context.lookup(JNDI_LOOKUP_SERVICE);
			if(lookup != null){
				dataSource =(DataSource)lookup;
			}else{
				new RuntimeException("DataSource not found!!!");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
