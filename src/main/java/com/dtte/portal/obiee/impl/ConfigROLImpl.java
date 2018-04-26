package com.dtte.portal.obiee.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigROLDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.utils.DBUtil;

public class ConfigROLImpl implements ConfigROLDAO {

	@Override
	public List<PORTALBI_CONFIGROL> listAllConfigsRol() {
		List<PORTALBI_CONFIGROL> listConfig = new ArrayList<>();
		String sql = "SELECT * FROM PORTALBI_CONFIGROL order by ID_CONFIGROL";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {

				long id = resultSet.getLong("ID_CONFIGROL");
				String parametro = resultSet.getString("PARAMETRO");
				String nombre = resultSet.getString("NOMBRE");
				PORTALBI_CONFIGROL configRol = new PORTALBI_CONFIGROL(id, parametro, nombre);
				listConfig.add(configRol);
			}
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones de Roles" + e);
			e.printStackTrace();
		}
		return listConfig;
	}

	@Override
	public int getLastCounter() {
		String sql = "select max (ID_CONFIGROL) from PORTALBI_CONFIGROL";
		int maximo = 0;
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
				maximo = result.getInt(1);
				System.out.println("El máximo es: " + maximo);

			}

		} catch (Exception e) {
			System.out.println("Excepcion al obtener el máximo registro" + e);
			e.printStackTrace();
		}
		return maximo;
	}

	@Override
	public boolean insert(PORTALBI_CONFIGROL configuracion) {
		boolean bandera = false;
		String sql = "insert into PORTALBI_CONFIGROL (ID_CONFIGROL,NOMBRE,PARAMETRO) VALUES (?,?,?)";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Insertando registro de CONFIGROL");
			ps.setLong(1, configuracion.getIdConfigrol());
			ps.setString(2, configuracion.getNombre());
			ps.setString(3, configuracion.getParametro());

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return bandera;
	}

	@Override
	public boolean delete(Long entificador) {
		boolean bandera = false;
		String sql = "delete from PORTALBI_CONFIGROL where ID_CONFIGROL=?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Borrando registro de CONFIGROL");
			ps.setLong(1, entificador);

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al borrar registro" + e);
		}
		return bandera;
	}

	@Override
	public PORTALBI_CONFIGROL getConfiguration(Long configuracionRol) {
		PORTALBI_CONFIGROL configRol = new PORTALBI_CONFIGROL();
		String sql = "SELECT * FROM PORTALBI_CONFIGROL where ID_CONFIGROL=" + configuracionRol;
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {

				long idRol = resultSet.getLong("ID_CONFIGROL");
				String parametro = resultSet.getString("PARAMETRO");
				String nombre = resultSet.getString("NOMBRE");

				configRol.setIdConfigrol(idRol);
				configRol.setNombre(nombre);
				configRol.setParametro(parametro);
			}
		} catch (Exception e) {
			System.out.println("Excepción al consultar configuración " + e);
			e.printStackTrace();
		}
		return configRol;
	}

	@Override
	public boolean update(PORTALBI_CONFIGROL newConfigRol) {
		boolean bandera = false;
		String sql = "update PORTALBI_CONFIGROL set PARAMETRO=?,NOMBRE=? WHERE ID_CONFIGROL=?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Actualizando registro de configROL");			
			
			ps.setLong(3, newConfigRol.getIdConfigrol());
			ps.setString(1, newConfigRol.getParametro());
			ps.setString(2, newConfigRol.getNombre());

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return bandera;
	}

	@Override
	public PORTALBI_CONFIGROL ObtainRolByName(String nombreRol) {
		PORTALBI_CONFIGROL configRol = new PORTALBI_CONFIGROL();
		String sql = "select * from PORTALBI_CONFIGROL where NOMBRE='"+nombreRol+"'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {

				long idRol = resultSet.getLong("ID_CONFIGROL");
				String parametro = resultSet.getString("PARAMETRO");
				String nombre = resultSet.getString("NOMBRE");

				configRol.setIdConfigrol(idRol);
				configRol.setNombre(nombre);
				configRol.setParametro(parametro);
			}
		} catch (Exception e) {
			System.out.println("Excepción al consultar configuración por Rol " + e);
			e.printStackTrace();
		}
		return configRol;
	}

}
