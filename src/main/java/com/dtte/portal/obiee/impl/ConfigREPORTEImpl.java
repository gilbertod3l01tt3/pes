package com.dtte.portal.obiee.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigREPORTEDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ConnectionManager;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;
import com.dtte.portal.obiee.utils.DBUtil;

public class ConfigREPORTEImpl implements ConfigREPORTEDAO {
	
	@Override
	public List<PORTALBI_CONFIGREPORTE> listAllConfigReporte() {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			List<PORTALBI_CONFIGREPORTE> result = conn.getSession().selectList("CONFIGREPORTE.selectAll");
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones de reportes" + e);
			e.printStackTrace();
		}
		return new ArrayList<PORTALBI_CONFIGREPORTE>();
	}

	@Override
	public PORTALBI_CONFIGREPORTE getConfiguration(Long ID) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_CONFIGREPORTE result = conn.getSession().selectOne("CONFIGREPORTE.selectById", ID);
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al consultar configuracion por ID" + e);
			e.printStackTrace();
		}
		return new PORTALBI_CONFIGREPORTE();
	}

	@Override
	public boolean insert(PORTALBI_CONFIGREPORTE configuracion) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().insert("CONFIGREPORTE.insert", configuracion);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al insertar registro" + e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Long identificador) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().delete("CONFIGREPORTE.delete", identificador);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al borrar registro" + e);
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(PORTALBI_CONFIGREPORTE newConfigReporte) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().update("CONFIGREPORTE.update", newConfigReporte);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al actualizar registro" + e);
			e.printStackTrace();
		}
		return false;
	}
	
	public String[] getParametersById(Long report) {
		String[] parametros = null;
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			String result = conn.getSession().selectOne("CONFIGREPORTE.selectParamById");
			parametros=result.split(",");
			return parametros;
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public int getMaxId() {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().selectOne("CONFIGREPORTE.selectMaxId");
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al obtener el maximo registro" + e);
			e.printStackTrace();
		}
		return -1;
	}
}
