package com.dtte.portal.obiee.dao;

import java.util.List;

import com.dtte.portal.obiee.model.Method_Response;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;

public interface RolREPORTEDAO {
	List<PORTALBI_ROLREPORTE> listAllConfigsReportesRol();
	List<PORTALBI_CONFIGROL> listAllConfigsRol();
	List<PORTALBI_CONFIGREPORTE> listAllConfigsReporte();

	int getLastCounter(long idRol);

	Method_Response insert(PORTALBI_ROLREPORTE configuracion);

	boolean delete(Long idRol, Long idReporte);

	PORTALBI_ROLREPORTE getConfiguration(Long configuracionRolId, Long reporteRolId);

	boolean update(PORTALBI_ROLREPORTE newConfigRol);

	PORTALBI_ROLREPORTE ObtainRolByName(String nombreRol);
	boolean CheckExists(long idRol, long idReporte);
	List<PORTALBI_ROLREPORTE> getReportesFromRol (Long rol);
	String getMandatoryParam(Long rol, Long reporte);
	String getOptionalParam(Long rol, Long reporte);
	String getNullParam(Long rol, Long reporte);
}
