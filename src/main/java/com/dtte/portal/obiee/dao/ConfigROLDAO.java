package com.dtte.portal.obiee.dao;

import java.util.List;

import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;

public interface ConfigROLDAO {

	List<PORTALBI_CONFIGROL> listAllConfigsRol();

	int getLastCounter();

	boolean insert(PORTALBI_CONFIGROL configuracion);

	boolean delete(Long identificador);

	PORTALBI_CONFIGROL getConfiguration(Long configuracionRolId);

	boolean update(PORTALBI_CONFIGROL newConfigRol);

}
