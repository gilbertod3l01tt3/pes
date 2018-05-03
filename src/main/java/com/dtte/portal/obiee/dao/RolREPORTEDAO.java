package com.dtte.portal.obiee.dao;

import java.util.List;

import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;

public interface RolREPORTEDAO {
	List<PORTALBI_ROLREPORTE> getReportesFromRol (Long rol);
	String[] getMandatoryParam(Long rol, Long reporte);
	String[] getOptionalParam(Long rol, Long reporte);
	String[] getNullParam(Long rol, Long reporte);
}
