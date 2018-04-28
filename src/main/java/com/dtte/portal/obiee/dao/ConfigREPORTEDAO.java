package com.dtte.portal.obiee.dao;

import java.util.List;

import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;

public interface ConfigREPORTEDAO {

	List<PORTALBI_CONFIGREPORTE> listAllConfigReporte();

	PORTALBI_CONFIGREPORTE getConfiguration(Long ID);

	boolean insert(PORTALBI_CONFIGREPORTE newReporteConfig);

	boolean delete(Long identifier);
}
