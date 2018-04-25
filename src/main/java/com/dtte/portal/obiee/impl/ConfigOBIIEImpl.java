package com.dtte.portal.obiee.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.ConfigOBIIEDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;
import com.dtte.portal.obiee.utils.DBUtil;

public class ConfigOBIIEImpl implements ConfigOBIIEDAO {

	@Override
	public List<PORTALBI_CONFIGOBIEE> listAllConfigs() throws SQLException {
		List<PORTALBI_CONFIGOBIEE> listConfig = new ArrayList<>();
		String sql = "SELECT * FROM PORTALBI_CONFIGOBIEE";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {

				long id = resultSet.getLong("ID_CONFIGOBIEE");
				String parametro = resultSet.getString("PARAMETRO");
				String valor = resultSet.getString("VALOR");
				// System.out.println("id: " + id);
				PORTALBI_CONFIGOBIEE config = new PORTALBI_CONFIGOBIEE(id, parametro, valor);
				// System.out.println("ID_CONFIGOBIEE: " +
				// config.getIdConfigobiee().toString());
				// System.out.println("PARAMETRO: " + config.getParametro());
				// System.out.println("VALOR: " + config.getValor());
				listConfig.add(config);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listConfig;
	}

	@Override
	public PORTALBI_CONFIGOBIEE getConfiguration(long ID_CONFIGOBIEE) throws SQLException {
		PORTALBI_CONFIGOBIEE config =new PORTALBI_CONFIGOBIEE();
		String sql = "SELECT * FROM PORTALBI_CONFIGOBIEE where ID_CONFIGOBIEE="+ID_CONFIGOBIEE;
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
			e.printStackTrace();
		}
		return config;
	}

	@Override
	public int getLastCounter() throws SQLException {
		String sql = "select max (ID_CONFIGOBIEE) from PORTALBI_CONFIGOBIEE";
		int maximo = 0;
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
				maximo = result.getInt(1);
				System.out.println("El máximo es: " + maximo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return maximo;
	}

	@Override
	public boolean insert(PORTALBI_CONFIGOBIEE configuracion) {
		boolean bandera = false;
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
		return bandera;
	}

	@Override
	public boolean update(PORTALBI_CONFIGOBIEE configuracion) {
		boolean bandera = false;
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
		return bandera;
	}
	
	@Override
	public boolean delete(Long identificador) {
		boolean bandera = false;
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
		return bandera;
	}

}
