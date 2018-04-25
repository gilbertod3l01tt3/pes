package com.dtte.portal.obiee.dao;

import java.sql.SQLException;
import java.util.List;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;

public interface ConfigOBIIEDAO {

	List<PORTALBI_CONFIGOBIEE> listAllConfigs() throws SQLException;

	int getLastCounter() throws SQLException;

	boolean insert(PORTALBI_CONFIGOBIEE configuracion);

	boolean update(PORTALBI_CONFIGOBIEE configuracion);

	PORTALBI_CONFIGOBIEE getConfiguration(long ID_CONFIGOBIEE) throws SQLException;

	boolean delete(Long identificador);

}
