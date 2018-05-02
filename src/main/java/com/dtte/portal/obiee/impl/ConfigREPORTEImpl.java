package com.dtte.portal.obiee.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigREPORTEDAO;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;
import com.dtte.portal.obiee.utils.DBUtil;

public class ConfigREPORTEImpl implements ConfigREPORTEDAO {
	
	public String[] getParametersById(Long reporte) {
		String[] parametros = null;
		String parametro = null;
		String sql = "select PARAMETRO from PORTALBI_CONFIGREPORTE where ID_CONFIGREPORTE='"+reporte.toString()+"'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
			    parametro = result.getString(1);								
			}
			parametros=parametro.split(",");
			System.out.println(parametros);
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		return parametros;
	}
	

}
