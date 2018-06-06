package com.dtte.portal.obiee.dao;

import java.util.List;

import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;

public interface ConfigROLDAO {

	List<PORTALBI_CONFIGROL> listAllConfigsRol();

	int getMaxId();

	boolean insert(PORTALBI_CONFIGROL configuracion);

	boolean delete(Long identificador);	

	boolean update(PORTALBI_CONFIGROL newConfigRol);
	
	PORTALBI_CONFIGROL getConfiguration(Long configuracionRolId);

	PORTALBI_CONFIGROL ObtainRolByName(String nombreRol);

}
