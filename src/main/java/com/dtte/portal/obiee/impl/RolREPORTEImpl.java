package com.dtte.portal.obiee.impl;

import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.RolREPORTEDAO;
import com.dtte.portal.obiee.model.Method_Response;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;
import com.dtte.portal.obiee.model.Method_Response.ResponseCodes;
import com.dtte.portal.obiee.model.PORTALBI_ConnectionManager;

public class RolREPORTEImpl implements RolREPORTEDAO {
	
	public List<PORTALBI_ROLREPORTE> listAllConfigsReportesRol() {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			List<PORTALBI_ROLREPORTE> result = conn.getSession().selectList("ROLREPORTE.selectAll");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ArrayList<PORTALBI_ROLREPORTE>();
		
	}
	
	@Override
	/**
	 * Lista todos los elementos de la tabla PORTALBI_CONFIGROL.
	 */
	public List<PORTALBI_CONFIGROL> listAllConfigsRol() {
		ConfigROLImpl configRol = new ConfigROLImpl();
		return configRol.listAllConfigsRol();
	}

	@Override
	/**
	 * Lista todos los elementos de la tabla PORTALBI_CONFIGREPORTE.
	 */
	public List<PORTALBI_CONFIGREPORTE> listAllConfigsReporte() {
		ConfigREPORTEImpl configReporte = new ConfigREPORTEImpl();
		return configReporte.listAllConfigReporte();
	}

	@Override
	public int selectMaxId(long rolId) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().selectOne("ROLREPORTE.selectLastCounter");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public Method_Response insert(PORTALBI_ROLREPORTE newRegister) {
		Method_Response result = new Method_Response();
		boolean exists = CheckExists(newRegister.getIdRol(), newRegister.getIdReporte());
		if (exists) {
			result.setCode(ResponseCodes.DUPLICATE_REGISTER);
			result.setMessage("Registro ya existente.");
			return result;
		}
		
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int rows = conn.getSession().insert("ROLREPORTE.insert", newRegister);
			
			if (rows <= 0) {
				result.setCode(ResponseCodes.GENERIC_ERROR);
				return result;
			}
			
			conn.getSession().commit();
		} catch (Exception e) {
			result.setCode(ResponseCodes.DUPLICATE_REGISTER);
			System.err.println("Excepcion al insertar registro - " + e);
		}
		
		return result;
	}

	@Override
	public boolean delete(Long idRol, Long idReporte) {
		Method_Response result = new Method_Response();
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_ROLREPORTE p = new PORTALBI_ROLREPORTE();
			p.setIdRol(idRol);
			p.setIdReporte(idReporte);
			
			int rows = conn.getSession().delete("ROLREPORTE.delete", p);
			if (rows <= 0) {
				result.setCode(ResponseCodes.GENERIC_ERROR);
				return false;
			}
			
			conn.getSession().commit();
			return true;
		} catch (Exception e) {
			result.setCode(ResponseCodes.GENERIC_ERROR);
			System.err.println("Excepcion al eliminar registro - " + e);
		}
		
		return false;
	}

	@Override
	public PORTALBI_ROLREPORTE getConfiguration(Long configuracionRol, Long idReporte) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_ROLREPORTE p = new PORTALBI_ROLREPORTE();
			p.setIdRol(configuracionRol);
			p.setIdReporte(idReporte);
			PORTALBI_ROLREPORTE result = conn.getSession().selectOne("ROLREPORTE.selectSingle", p);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean update(PORTALBI_ROLREPORTE newRegister) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().update("ROLREPORTE.update", newRegister);
			
			if (result > 0) {
				conn.getSession().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public PORTALBI_ROLREPORTE ObtainRolByName(String nombreDesp) {
		
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_ROLREPORTE result = conn.getSession().selectOne("ROLREPORTE.selectSingleByNombre", nombreDesp);
			return result;
		} catch (Exception e) {
			System.out.println("Excepción al consultar configuración por Rol " + e);
			e.printStackTrace();
		}
		
		return new PORTALBI_ROLREPORTE();
		
	}
	
	@Override
	public boolean CheckExists(long idRol, long idReporte) {
		return getConfiguration(idRol, idReporte) != null;
	}
	
	
	@Override
	public List<PORTALBI_ROLREPORTE> getReportesFromRol(Long rol) {
		List<PORTALBI_ROLREPORTE> reportsList = new ArrayList<>();
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			reportsList = conn.getSession().selectList("ROLREPORTE.selectReportsByRol", rol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reportsList;
	}

	@Override
	public String getMandatoryParam(Long rol, Long reporte) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_ROLREPORTE p = new PORTALBI_ROLREPORTE();
			p.setIdRol(rol);
			p.setIdReporte(reporte);
			PORTALBI_ROLREPORTE result = conn.getSession().selectOne("ROLREPORTE.selectMandatory", p);
			return result.getParametrosMandatorios();
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getOptionalParam(Long rol, Long reporte) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_ROLREPORTE p = new PORTALBI_ROLREPORTE();
			p.setIdRol(rol);
			p.setIdReporte(reporte);
			PORTALBI_ROLREPORTE result = conn.getSession().selectOne("ROLREPORTE.selectOptional", p);
			return result.getParametrosOpcionales();
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getNullParam(Long rol, Long reporte) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_ROLREPORTE p = new PORTALBI_ROLREPORTE();
			p.setIdRol(rol);
			p.setIdReporte(reporte);
			PORTALBI_ROLREPORTE result = conn.getSession().selectOne("ROLREPORTE.selectNull", p);
			return result.getParametrosNulos();
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		
		return null;
	}

}
