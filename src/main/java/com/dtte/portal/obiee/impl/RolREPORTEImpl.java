package com.dtte.portal.obiee.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dtte.portal.obiee.dao.RolREPORTEDAO;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;
import com.dtte.portal.obiee.utils.DBUtil;

public class RolREPORTEImpl implements RolREPORTEDAO {

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
	public String[] getMandatoryParam(Long rol, Long reporte) {
		// TODO Auto-generated method stub
		String[] parametros = null;
		String parametro = null;
		String sql = "select PARAMETROMANDATORIO from PORTALBI_ROLREPORTE "
				+ "where ID_CONFIGROL='"+rol.toString()+"' "
				+ "and ID_CONFIGREPORTE='"+reporte.toString()+"'";
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
		return parametros;
	}

	@Override
	public String[] getOptionalParam(Long rol, Long reporte) {
		// TODO Auto-generated method stub
		String[] parametros = null;
		String parametro = null;
		String sql = "select PARAMETROOPCIONAL from PORTALBI_ROLREPORTE "
				+ "where ID_CONFIGROL='"+rol.toString()+"' "
				+ "and ID_CONFIGREPORTE='"+reporte.toString()+"'";
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
		return parametros;
	}

	@Override
	public String[] getNullParam(Long rol, Long reporte) {
		// TODO Auto-generated method stub
		String[] parametros = null;
		String parametro = null;
		String sql = "select PARAMETRONULO from PORTALBI_ROLREPORTE "
				+ "where ID_CONFIGROL='"+rol.toString()+"' "
				+ "and ID_CONFIGREPORTE='"+reporte.toString()+"'";
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
		return parametros;
	}

}
