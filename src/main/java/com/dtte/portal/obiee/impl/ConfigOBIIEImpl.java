package com.dtte.portal.obiee.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigOBIIEDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;
import com.dtte.portal.obiee.utils.DBUtil;

public class ConfigOBIIEImpl implements ConfigOBIIEDAO{

	@Override
	public List<PORTALBI_CONFIGOBIEE> listAllConfigs() throws SQLException {
		List<PORTALBI_CONFIGOBIEE> listConfig = new ArrayList<>();		
		String sql = "SELECT * FROM PORTALBI_CONFIGOBIEE";
		try(java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement =connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
				
				while (resultSet.next()) {
					
					long id = resultSet.getLong("ID_CONFIGOBIEE");
					String parametro = resultSet.getString("PARAMETRO");
					String valor = resultSet.getString("VALOR");
										
					PORTALBI_CONFIGOBIEE config=new PORTALBI_CONFIGOBIEE(id,parametro,valor);
					listConfig.add(config);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return listConfig;
	}

}
