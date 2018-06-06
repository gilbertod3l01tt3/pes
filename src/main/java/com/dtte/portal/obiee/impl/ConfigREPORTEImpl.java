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
		/*List<PORTALBI_CONFIGREPORTE> listConfig = new ArrayList<>();
		String sql = "SELECT * FROM PORTALBI_CONFIGREPORTE order by ID_CONFIGREPORTE";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {

				long id = resultSet.getLong("ID_CONFIGREPORTE");
				String nombre = resultSet.getString("NOMBRE");
				int estatus = resultSet.getInt("ESTATUS");
				String parametro = resultSet.getString("PARAMETRO");
				String path = resultSet.getString("PATH");
				String panel = resultSet.getString("PANEL");

				PORTALBI_CONFIGREPORTE configRol = new PORTALBI_CONFIGREPORTE(id, nombre, estatus, parametro, path,
						panel);
				listConfig.add(configRol);
			}
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones de Reportes" + e);
			e.printStackTrace();
		}
		return listConfig;*/
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
		/*PORTALBI_CONFIGREPORTE configReporte = new PORTALBI_CONFIGREPORTE();
		String sql = "SELECT * FROM PORTALBI_CONFIGREPORTE where ID_CONFIGREPORTE=" + ID;
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {

				long idRol = resultSet.getLong("ID_CONFIGREPORTE");
				String nombre = resultSet.getString("NOMBRE");
				int estatus = resultSet.getInt("ESTATUS");
				String parametro = resultSet.getString("PARAMETRO");
				String path = resultSet.getString("PATH");
				String panel = resultSet.getString("PANEL");

				configReporte.setIdConfigreporte(idRol);
				configReporte.setNombre(nombre);
				configReporte.setEstatus(estatus);
				configReporte.setParametro(parametro);
				configReporte.setPanel(panel);
				configReporte.setPath(path);
			}
		} catch (Exception e) {
			System.out.println("Excepción al consultar configuración " + e);
			e.printStackTrace();
		}
		return configReporte;*/
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
		/*String sql = "insert into PORTALBI_CONFIGREPORTE (ID_CONFIGREPORTE,NOMBRE,ESTATUS,PARAMETRO,PATH,PANEL) VALUES (?,?,?,?,?,?)";
		boolean bandera=false;
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Insertando registro de CONFIGREPORTE");
						
			ps.setLong(1, newReporteConfig.getIdConfigreporte());
			ps.setString(2, newReporteConfig.getNombre());
			ps.setInt(3, newReporteConfig.getEstatus());
			ps.setString(4, newReporteConfig.getParametro());
			ps.setString(5, newReporteConfig.getPath());
			ps.setString(6, newReporteConfig.getPanel());
			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return bandera;*/
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
		/*boolean bandera = false;
		String sql = "delete from PORTALBI_CONFIGREPORTE where ID_CONFIGREPORTE=?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Borrando registro de CONFIGREPORTE");
			ps.setLong(1, identifier);

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al borrar registro" + e);
		}
		return bandera;*/
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
		/*boolean bandera = false;
		String sql = "update PORTALBI_CONFIGREPORTE set PARAMETRO=?,NOMBRE=?,PANEL=?,PATH=?,ESTATUS=? WHERE ID_CONFIGREPORTE=?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Actualizando registro de configReporte");

			ps.setString(1,newReporteConfig.getParametro());
			ps.setString(2,newReporteConfig.getNombre());
			ps.setString(3,newReporteConfig.getPanel());
			ps.setString(4,newReporteConfig.getPath());
			ps.setInt(5, newReporteConfig.getEstatus());
			ps.setLong(6, newReporteConfig.getIdConfigreporte());
			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return bandera;*/
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
		/*String parametro = null;
		String sql = "select PARAMETRO from PORTALBI_CONFIGREPORTE where ID_CONFIGREPORTE='"+report.toString()+"'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
			    parametro = result.getString(1);								
			}
			parametros=parametro.split(",");
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		return parametros;*/
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
		/*String sql = "select max (ID_CONFIGREPORTE) from PORTALBI_CONFIGREPORTE";
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
			int result = conn.getSession().selectOne("CONFIGREPORTE.selectMaxId");
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion al obtener el maximo registro" + e);
			e.printStackTrace();
		}
		return -1;
	}
}
