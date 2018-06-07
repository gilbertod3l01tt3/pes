package com.dtte.portal.obiee.impl;

import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigOBIIEDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;
import com.dtte.portal.obiee.model.PORTALBI_ConnectionManager;


public class ConfigOBIIEImpl implements ConfigOBIIEDAO {

	@Override
	public List<PORTALBI_CONFIGOBIEE> listAllConfigs() {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			List<PORTALBI_CONFIGOBIEE> result = conn.getSession().selectList("CONFIGOBIEE.selectAll");
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones de Roles" + e);
			e.printStackTrace();
		}
		return new ArrayList<PORTALBI_CONFIGOBIEE>();
	}

	@Override
	public PORTALBI_CONFIGOBIEE getConfiguration(long ID) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_CONFIGOBIEE result = conn.getSession().selectOne("CONFIGOBIEE.selectById", ID);
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al consultar configuracion por ID" + e);
			e.printStackTrace();
		}
		return new PORTALBI_CONFIGOBIEE();
	}

	@Override
	public int getMaxId() {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().selectOne("CONFIGOBIEE.selectMaxId");
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al obtener el maximo registro" + e);
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean insert(PORTALBI_CONFIGOBIEE configuracion) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().insert("CONFIGOBIEE.insert", configuracion);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al insertar registro" + e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(PORTALBI_CONFIGOBIEE newConfigObiee) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().update("CONFIGOBIEE.update", newConfigObiee);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al actualizar registro" + e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Long identificador) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().delete("CONFIGOBIEE.delete", identificador);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al borrar registro" + e);
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String ObtainValue(String value) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			String result = conn.getSession().selectOne("CONFIGOBIEE.selectValue", value);
			conn.getSession().commit();
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al obtener valor" + e);
			e.printStackTrace();
		}
		return null;
	}

}
