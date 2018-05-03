package com.dtte.portal.obiee.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.ConfigOBIIEImpl;
import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.impl.RolREPORTEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;

public class GeneraURLDinamicaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RolREPORTEImpl reporteImpl = new RolREPORTEImpl();
	private ConfigROLImpl rolImpl = new ConfigROLImpl();
	private Common utils = new Common();
	private ConfigOBIIEImpl configImpl = new ConfigOBIIEImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=iso-8859-1");
		PrintWriter out = response.getWriter();

		// Obteniendo el Rol a configurar

		String nombre = request.getParameter("rol");
		System.out.println("Obteniendo configuracion: " + nombre);
		PORTALBI_CONFIGROL rol = rolImpl.ObtainRolByName(nombre);
		System.out.println("Obteniendo datos desde la base: " + rol.getNombre());
		System.out.println("Obteniendo datos desde la base/parametro: " + rol.getParametro());

		// Obteniendo el id del reporte
		// String idreporte = request.getParameter("reporte");
		// Obteniendo los parámetros mandatorios del reporte
		// Obteniendo los parámetros opcionales del reporte
		// Obteniendo los parámetros nulos del reporte

		// Traer los datos de conexión del Rol
		Map<String, String> variablesPorReporte = utils.getVariablesAndValues(rol.getParametro());
		StringBuilder urlEstatica = utils.buildPlainURL(configImpl.ObtainValue("servidor"),
				configImpl.ObtainValue("PUERTO"), variablesPorReporte.get("NQUser"),
				variablesPorReporte.get("NQPassword"));
		System.out.println(urlEstatica.toString());

		// Generar cadena de conexión variable
		// Hacer encoding a la parte variable
		// Concatenar datos de conexión y variables

		List<PORTALBI_ROLREPORTE> reportes = reporteImpl.getReportesFromRol(rol.getIdConfigrol());

		out.println("<p><strong>Seleccione el reporte que desea visualizar.</strong></p>");
	}

	@Override
	public String getServletInfo() {
		return "Servlet que construye la URL dinámicamente para consumir al OBIEE";
	}

}
