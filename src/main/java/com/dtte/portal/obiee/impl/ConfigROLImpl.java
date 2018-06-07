package com.dtte.portal.obiee.impl;

import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigROLDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ConnectionManager;

public class ConfigROLImpl implements ConfigROLDAO {

	@Override
	public List<PORTALBI_CONFIGROL> listAllConfigsRol() {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			List<PORTALBI_CONFIGROL> result = conn.getSession().selectList("CONFIGROL.selectAll");
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones de Roles" + e);
			e.printStackTrace();
		}
		return new ArrayList<PORTALBI_CONFIGROL>();	
	}

	@Override
	public int getMaxId() {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().selectOne("CONFIGROL.selectMaxId");
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al obtener el maximo registro" + e);
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public boolean insert(PORTALBI_CONFIGROL configuracion) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().insert("CONFIGROL.insert", configuracion);
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
			int result = conn.getSession().delete("CONFIGROL.delete", identificador);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al borrar registro" + e);
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean update(PORTALBI_CONFIGROL newConfigRol) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			int result = conn.getSession().update("CONFIGROL.update", newConfigRol);
			conn.getSession().commit();
			return result > 0;
		} catch (Exception e) {
			System.out.println("Excepcion al actualizar registro" + e);
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public PORTALBI_CONFIGROL getConfiguration(Long ID) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_CONFIGROL result = conn.getSession().selectOne("CONFIGROL.selectById", ID);
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al consultar configuracion por ID" + e);
			e.printStackTrace();
		}
		return new PORTALBI_CONFIGROL();
	}

	@Override
	public PORTALBI_CONFIGROL ObtainRolByName(String nombreRol) {
		try (PORTALBI_ConnectionManager conn = new PORTALBI_ConnectionManager()) {
			PORTALBI_CONFIGROL result = conn.getSession().selectOne("CONFIGROL.selectByName", nombreRol.toUpperCase());
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al consultar configuracion por rol " + e);
			e.printStackTrace();
		}
		return new PORTALBI_CONFIGROL();		
	}

}
