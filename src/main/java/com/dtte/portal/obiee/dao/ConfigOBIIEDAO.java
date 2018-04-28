package com.dtte.portal.obiee.dao;

import java.util.List;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;

public interface ConfigOBIIEDAO {

	List<PORTALBI_CONFIGOBIEE> listAllConfigs();

	int getLastCounter();

	boolean insert(PORTALBI_CONFIGOBIEE configuracion);

	boolean update(PORTALBI_CONFIGOBIEE configuracion);

	PORTALBI_CONFIGOBIEE getConfiguration(long ID_CONFIGOBIEE);

	boolean delete(Long identificador);

	String ObtainValue(String parameter);

}
