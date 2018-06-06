package com.dtte.portal.obiee.impl;

import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigOBIIEDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;
import com.dtte.portal.obiee.model.PORTALBI_ConnectionManager;


public class ConfigOBIIEImpl implements ConfigOBIIEDAO {

	@Override
	public List<PORTALBI_CONFIGOBIEE> listAllConfigs() {
		/*List<PORTALBI_CONFIGOBIEE> listConfig = new ArrayList<>();
		String sql = "SELECT * FROM PORTALBI_CONFIGOBIEE ORDER BY ID_CONFIGOBIEE";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {

				long id = resultSet.getLong("ID_CONFIGOBIEE");
				String parametro = resultSet.getString("PARAMETRO");
				String valor = resultSet.getString("VALOR");
				PORTALBI_CONFIGOBIEE config = new PORTALBI_CONFIGOBIEE(id, parametro, valor);
				listConfig.add(config);
			}
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones" + e);
			e.printStackTrace();
		}
		return listConfig;*/
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
		/*PORTALBI_CONFIGOBIEE config = new PORTALBI_CONFIGOBIEE();
		String sql = "SELECT * FROM PORTALBI_CONFIGOBIEE where ID_CONFIGOBIEE=" + ID_CONFIGOBIEE;
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {

				long id = resultSet.getLong("ID_CONFIGOBIEE");
				String parametro = resultSet.getString("PARAMETRO");
				String valor = resultSet.getString("VALOR");

				config.setIdConfigobiee(id);
				config.setParametro(parametro);
				config.setValor(valor);

			}
		} catch (Exception e) {
			System.out.println("Excepción al consultar configuración " + e);
			e.printStackTrace();
		}
		return config;*/
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
		/*String sql = "select max (ID_CONFIGOBIEE) from PORTALBI_CONFIGOBIEE";
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
		return maximo;*/
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
		/*boolean bandera = false;
		String sql = "insert into PORTALBI_CONFIGOBIEE (ID_CONFIGOBIEE,PARAMETRO,VALOR) VALUES (?,?,?)";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Insertando registro de configOBIEE");
			ps.setLong(1, configuracion.getIdConfigobiee());
			ps.setString(2, configuracion.getParametro());
			ps.setString(3, configuracion.getValor());

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return bandera;*/
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
		/*boolean bandera = false;
		String sql = "update PORTALBI_CONFIGOBIEE set PARAMETRO=?,VALOR=? WHERE ID_CONFIGOBIEE=?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Actualizando registro de configOBIEE");
			ps.setLong(3, configuracion.getIdConfigobiee());
			ps.setString(1, configuracion.getParametro());
			ps.setString(2, configuracion.getValor());

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return bandera;*/
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
		/*boolean bandera = false;
		String sql = "delete from PORTALBI_CONFIGOBIEE where ID_CONFIGOBIEE=?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Borrando registro de configOBIEE");
			ps.setLong(1, identificador);

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al borrar registro" + e);
		}
		return bandera;*/
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
		/*String sql = "select valor from PORTALBI_CONFIGOBIEE where PARAMETRO='" + parameter + "'";
		String resultado = null;
		System.out.println("El valor del parámetro: " + parameter + " es: " + resultado);
		System.out.println("El query a ejecutar es: " + sql);
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {
			while (result.next()) {
				resultado = result.getString(1);
			}

		} catch (Exception e) {
			System.out.println("Excepcion al obtener el valor del parámetro: " + parameter + e);
			e.printStackTrace();
		}
		return resultado;*/
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
