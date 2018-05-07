package com.dtte.portal.obiee.dao;

import java.util.List;

import com.dtte.portal.obiee.model.Method_Response;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTEROL;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;

public interface ConfigREPORTEROLDAO {

	List<PORTALBI_CONFIGREPORTEROL> listAllConfigsReportesRol();
	List<PORTALBI_CONFIGROL> listAllConfigsRol();
	List<PORTALBI_CONFIGREPORTE> listAllConfigsReporte();

	int getLastCounter(long idRol);

	Method_Response insert(PORTALBI_CONFIGREPORTEROL configuracion);

	boolean delete(Long idRol, Long idReporte);

	PORTALBI_CONFIGREPORTEROL getConfiguration(Long configuracionRolId, Long reporteRolId);

	boolean update(PORTALBI_CONFIGREPORTEROL newConfigRol);

	PORTALBI_CONFIGREPORTEROL ObtainRolByName(String nombreRol);
	boolean CheckExists(long idRol, long idReporte);

}
