package com.dtte.portal.obiee.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.RolREPORTEDAO;
import com.dtte.portal.obiee.model.Method_Response;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;
import com.dtte.portal.obiee.model.Method_Response.ResponseCodes;
import com.dtte.portal.obiee.model.PORTALBI_ConnectionManager;
import com.dtte.portal.obiee.utils.DBUtil;

public class RolREPORTEImpl implements RolREPORTEDAO {
	
	public List<PORTALBI_ROLREPORTE> listAllConfigsReportesRol() {
//		List<PORTALBI_ROLREPORTE> listConfig = new ArrayList<>();
//		String sql = "SELECT * FROM PORTALBI_ROLREPORTE order by ID_CONFIGROL, ID_CONFIGREPORTE";
//		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
//				java.sql.Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery(sql)) {
//
//			while (resultSet.next()) {
//				long idRol = resultSet.getLong("ID_CONFIGROL");
//				long idReporte = resultSet.getLong("ID_CONFIGREPORTE");
//				String nombreDespliegue = resultSet.getString("NOMBREDESPLIEGUE");
//				int ordenDespliegue = resultSet.getInt("ORDENDESPLIEGUE");
//				String paramOpcionales = resultSet.getString("PARAMETROOPCIONAL");
//				String paramMandatorios = resultSet.getString("PARAMETROMANDATORIO");
//				String paramNulos = resultSet.getString("PARAMETRONULO");
//				String pagina = resultSet.getString("PAGINA");
//				PORTALBI_ROLREPORTE configReporteRol = new PORTALBI_ROLREPORTE(idRol, idReporte, nombreDespliegue, ordenDespliegue, 
//						paramOpcionales, paramMandatorios, paramNulos, pagina);
//				listConfig.add(configReporteRol);
//			}
//		} catch (Exception e) {
//			System.out.println("Excepcion al traer las configuraciones de Reportes-Roles" + e);
//			e.printStackTrace();
//		}
//		return listConfig;
		
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
		List<PORTALBI_CONFIGROL> listConfig = new ArrayList<>();
		String sql = "SELECT * FROM PORTALBI_CONFIGROL order by ID_CONFIGROL";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				long idRol = resultSet.getLong("ID_CONFIGROL");
				String nombre = resultSet.getString("NOMBRE");
				String parametro = resultSet.getString("PARAMETRO");
				PORTALBI_CONFIGROL configReporteRol = new PORTALBI_CONFIGROL(idRol, parametro, nombre);
				listConfig.add(configReporteRol);
			}
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones de Reportes-Roles" + e);
			e.printStackTrace();
		}
		return listConfig;
	}

	@Override
	/**
	 * Lista todos los elementos de la tabla PORTALBI_CONFIGREPORTE.
	 */
	public List<PORTALBI_CONFIGREPORTE> listAllConfigsReporte() {
		List<PORTALBI_CONFIGREPORTE> listConfig = new ArrayList<>();
		String sql = "SELECT * FROM PORTALBI_CONFIGREPORTE order by ID_CONFIGREPORTE";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				long idReporte = resultSet.getLong("ID_CONFIGREPORTE");
				String nombre = resultSet.getString("NOMBRE");
				int estatus = resultSet.getInt("ESTATUS");
				String parametro = resultSet.getString("PARAMETRO");
				String path = resultSet.getString("PATH");
				String panel = resultSet.getString("PANEL");
				PORTALBI_CONFIGREPORTE configReporteRol = new PORTALBI_CONFIGREPORTE(idReporte, nombre, estatus, parametro, path, panel);
				listConfig.add(configReporteRol);
			}
		} catch (Exception e) {
			System.out.println("Excepcion al traer las configuraciones de Reportes-Roles" + e);
			e.printStackTrace();
		}
		return listConfig;
	}

	@Override
	public int getLastCounter(long rolId) {
		String sql = "select max (ID_CONFIGREPORTE) from PORTALBI_ROLREPORTE WHERE ID_CONFIGROL = " + rolId;
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
	public Method_Response insert(PORTALBI_ROLREPORTE newRegister) {
		Method_Response result = new Method_Response();
		
		String sql = "insert into PORTALBI_ROLREPORTE (ID_CONFIGROL,ID_CONFIGREPORTE,NOMBREDESPLIEGUE,"
				+ "ORDENDESPLIEGUE,PARAMETROMANDATORIO,PARAMETROOPCIONAL,PARAMETRONULO,PAGINA) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		boolean exists = CheckExists(newRegister.getIdRol(), newRegister.getIdReporte());
		if (exists) {
			result.setCode(ResponseCodes.DUPLICATE_REGISTER);
			result.setMessage("Registro ya existente.");
			return result;
		}
		
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Insertando registro de CONFIGROL");
			ps.setLong(1, newRegister.getIdRol());
			ps.setLong(2, newRegister.getIdReporte());
			ps.setString(3, newRegister.getNombreDespliegue());
			ps.setInt(4, newRegister.getOrdenDespliegue());
			ps.setString(5, newRegister.getParametrosMandatorios());
			ps.setString(6, newRegister.getParametrosOpcionales());
			ps.setString(7, newRegister.getParametrosNulos());
			ps.setString(8, newRegister.getPagina());

			if (ps.executeUpdate() <= 0)
				result.setCode(ResponseCodes.GENERIC_ERROR);
			
			connection.commit();
		} catch (Exception e) {
			result.setCode(ResponseCodes.DUPLICATE_REGISTER);
			System.out.println("Excepcion al insertar registro" + e);
		}
		return result;
	}

	@Override
	public boolean delete(Long idRol, Long idReporte) {
		boolean bandera = false;
		String sql = "delete from PORTALBI_ROLREPORTE where ID_CONFIGROL = ? and ID_CONFIGREPORTE = ?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Borrando registro de CONFIGROL");
			ps.setLong(1, idRol);
			ps.setLong(2, idReporte);

			bandera = ps.executeUpdate() > 0;
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al borrar registro" + e);
		}
		return bandera;
	}

	@Override
	public PORTALBI_ROLREPORTE getConfiguration(Long configuracionRol, Long reporteId) {
		PORTALBI_ROLREPORTE configRol = new PORTALBI_ROLREPORTE();
		String sql = "SELECT * FROM PORTALBI_ROLREPORTE where ID_CONFIGROL=" + configuracionRol + " AND ID_CONFIGREPORTE = " + reporteId;
		
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {

				long idRol = resultSet.getLong("ID_CONFIGROL");
				long idReporte = resultSet.getLong("ID_CONFIGREPORTE");
				String nombreDespliegue = resultSet.getString("NOMBREDESPLIEGUE");
				int ordenDespliegue = resultSet.getInt("ORDENDESPLIEGUE");
				String paramOpcionales = resultSet.getString("PARAMETROOPCIONAL");
				String paramMandatorios = resultSet.getString("PARAMETROMANDATORIO");
				String paramNulos = resultSet.getString("PARAMETRONULO");
				String pagina = resultSet.getString("PAGINA");

				configRol.setIdRol(idRol);
				configRol.setIdReporte(idReporte);
				configRol.setNombreDespliegue(nombreDespliegue);
				configRol.setOrdenDespliegue(ordenDespliegue);
				configRol.setPagina(pagina);
				configRol.setParametrosMandatorios(paramMandatorios);
				configRol.setParametrosNulos(paramNulos);
				configRol.setParametrosOpcionales(paramOpcionales);
			}
		} catch (Exception e) {
			System.out.println("Excepción al consultar configuración " + e);
			e.printStackTrace();
		}
		return configRol;
	}

	@Override
	public boolean update(PORTALBI_ROLREPORTE newRegister) {
		boolean bandera = false;
		String sql = "update PORTALBI_ROLREPORTE set NOMBREDESPLIEGUE=?,ORDENDESPLIEGUE=?,PARAMETROMANDATORIO=?,"
				+ "PARAMETROOPCIONAL=?, PARAMETRONULO=?, PAGINA=? WHERE ID_CONFIGROL=? AND ID_CONFIGREPORTE=?";

		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			System.out.println("Actualizando registro de configREPORTEROL");

			ps.setString(1, newRegister.getNombreDespliegue());
			ps.setInt(2, newRegister.getOrdenDespliegue());
			ps.setString(3, newRegister.getParametrosMandatorios());
			ps.setString(4, newRegister.getParametrosOpcionales());
			ps.setString(5, newRegister.getParametrosNulos());
			ps.setString(6, newRegister.getPagina());
			ps.setLong(7, newRegister.getIdRol());
			ps.setLong(8, newRegister.getIdReporte());

			bandera = ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return bandera;
	}

	@Override
	public PORTALBI_ROLREPORTE ObtainRolByName(String nombreDesp) {
		PORTALBI_ROLREPORTE configRol = new PORTALBI_ROLREPORTE();
		String sql = "select * from PORTALBI_ROLREPORTE where NOMBREDESPLIEGUE = '" + nombreDesp.toUpperCase() + "'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {
			while (resultSet.next()) {
				long idRol = resultSet.getLong("ID_CONFIGROL");
				long idReporte = resultSet.getLong("ID_CONFIGREPORTE");
				String nombreDespliegue = resultSet.getString("NOMBRE_DESPLIEGUE");
				int ordenDespliegue = resultSet.getInt("ORDEN_DESPLIEGUE");
				String paramOpcionales = resultSet.getString("PARAM_OPCIONAL");
				String paramMandatorios = resultSet.getString("PARAM_MANADATORIO");
				String paramNulos = resultSet.getString("PARAM_NULO");
				String pagina = resultSet.getString("PAGINA");

				configRol.setIdRol(idRol);
				configRol.setIdReporte(idReporte);
				configRol.setNombreDespliegue(nombreDespliegue);
				configRol.setOrdenDespliegue(ordenDespliegue);
				configRol.setPagina(pagina);
				configRol.setParametrosMandatorios(paramMandatorios);
				configRol.setParametrosNulos(paramNulos);
				configRol.setParametrosOpcionales(paramOpcionales);
			}
		} catch (Exception e) {
			System.out.println("Excepción al consultar configuración por Rol " + e);
			e.printStackTrace();
		}
		return configRol;
	}
	
	@Override
	public boolean CheckExists(long idRol, long idReporte) {
		String sql = "select ID_CONFIGROL, repo.ID_CONFIGREPORTE " + 
				"from PORTALBI_CONFIGROL rol " + 
				"INNER JOIN PORTALBI_CONFIGREPORTE repo ON repo.ID_CONFIGREPORTE = ? " + 
				"where rol.ID_CONFIGROL = ? and repo.ID_CONFIGREPORTE = ?";
		
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setLong(1, idRol);
			ps.setLong(2, idReporte);
			ps.setLong(3, idReporte);

			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Excepcion al insertar registro" + e);
		}
		return false;
	}
	
	
	@Override
	public List<PORTALBI_ROLREPORTE> getReportesFromRol(Long rol) {
		List<PORTALBI_ROLREPORTE> listReport = new ArrayList<>();
		String sql = "select * from PORTALBI_ROLREPORTE where ID_CONFIGROL='"+rol.toString()+"'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
				long idConfigrol = result.getLong(1);
				long idConfigreporte = result.getLong(2);
			    String nombredespliegue = result.getString(3);
			    int ordendespliegue = result.getInt(4);
			    String parametromandatorio = result.getString(5);
			    String parametroopcional = result.getString(6);
			    String parametronulo = result.getString(7);
			    String pagina = result.getString(8);
				PORTALBI_ROLREPORTE report = new PORTALBI_ROLREPORTE(idConfigrol,idConfigreporte,nombredespliegue,ordendespliegue,parametromandatorio,parametroopcional,parametronulo,pagina);
				listReport.add(report);
			}

		} catch (Exception e) {
			System.out.println("Excepcion al obtener reportes" + e);
			e.printStackTrace();
		}
		return listReport;
	}

	@Override
	public String getMandatoryParam(Long rol, Long reporte) {
		// TODO Auto-generated method stub
		String parametros = null;
		String sql = "select PARAMETROMANDATORIO from PORTALBI_ROLREPORTE "
				+ "where ID_CONFIGROL='"+rol.toString()+"' "
				+ "and ID_CONFIGREPORTE='"+reporte.toString()+"'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
			    parametros = result.getString(1);								
			}
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		return parametros;
	}

	@Override
	public String getOptionalParam(Long rol, Long reporte) {
		// TODO Auto-generated method stub
		String parametros = null;
		String sql = "select PARAMETROOPCIONAL from PORTALBI_ROLREPORTE "
				+ "where ID_CONFIGROL='"+rol.toString()+"' "
				+ "and ID_CONFIGREPORTE='"+reporte.toString()+"'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
			    parametros = result.getString(1);								
			}
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		return parametros;
	}

	@Override
	public String getNullParam(Long rol, Long reporte) {
		// TODO Auto-generated method stub
		String parametros = null;
		String sql = "select PARAMETRONULO from PORTALBI_ROLREPORTE "
				+ "where ID_CONFIGROL='"+rol.toString()+"' "
				+ "and ID_CONFIGREPORTE='"+reporte.toString()+"'";
		try (java.sql.Connection connection = DBUtil.getDataSource().getConnection();
				java.sql.Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {
			    parametros = result.getString(1);								
			}
		} catch (Exception e) {
			System.out.println("Excepcion al obtener parametros" + e);
			e.printStackTrace();
		}
		return parametros;
	}

}
