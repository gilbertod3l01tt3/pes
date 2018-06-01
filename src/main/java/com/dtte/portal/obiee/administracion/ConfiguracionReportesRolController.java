package com.dtte.portal.obiee.administracion;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.RolREPORTEImpl;
import com.dtte.portal.obiee.model.Method_Response.ResponseCodes;
import com.dtte.portal.obiee.model.Method_Response;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;

public class ConfiguracionReportesRolController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private RolREPORTEImpl Implementacion = new RolREPORTEImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String actionFromWeb = request.getServletPath();
		System.out.println("actionFromWeb: " + actionFromWeb);

		if (!actionFromWeb.toLowerCase().equals("/configuracionreportesrol"))
			throw new ServletException("Error en el llamado");

		try {
			String accion = request.getParameter("accion");
			System.out.println("accion: " + accion);

			switch (accion) {
			case "new":
				newForm(request, response);
				break;
			case "insert":
				insert(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "edit":
				edit(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "consultarRolPorNombre":
				consultarRolPorNombre(request, response);
				break;
			case "listar":
			default:
				listarConfiguraciones(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void consultarRolPorNombre(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nombreDespliegue = request.getParameter("NOMBRE_DESPLIEGUE");
		response.setContentType("text/html; charset=iso-8859-1");
		PORTALBI_ROLREPORTE valor = Implementacion.ObtainRolByName(nombreDespliegue);
		PrintWriter out = response.getWriter();
		out.println("Esto es una consulta del objeto Rol basado en el nombre");
		out.println("</br>");
//		out.println("Nombre del rol: " + parametroPrueba);
//		out.println("Desde el objeto/Nombre: " + valor.getNombre());
//		out.println("Desde el objeto/Parametro: " + valor.getParametro());
//		out.println("Desde el objeto/ID: " + valor.getIdConfigrol());

	}

	private void newForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		java.util.List<PORTALBI_CONFIGROL> roles = Implementacion.listAllConfigsRol();
		java.util.List<PORTALBI_CONFIGREPORTE> reportes = Implementacion.listAllConfigsReporte();
		request.setAttribute("configuracionReportes", reportes);
		request.setAttribute("configuracionRoles", roles);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/editarReportesRol.jsp");
		dispatcher.forward(request, response);
	}

	private void listarConfiguraciones(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		java.util.List<PORTALBI_ROLREPORTE> listaConf = Implementacion.listAllConfigsReportesRol();
		request.setAttribute("listaConfiguracionesRoles", listaConf);
		
		String html = GenerateHtml();
		request.setAttribute("dynamicReport", html);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/configuracionReportesRol.jsp");
		dispatcher.forward(request, response);
	}
	
	public String GenerateHtml() {
    	String content = null;
    	URLConnection connection = null;
    	try {
    		String path = "http://172.31.10.150:9502/analytics/saw.dll?Dashboard&NQUser=usr_Estado&NQPassword=usr_3st4d0&Action=Navigate&PortalPath=/shared/Carpeta%20de%20Pruebas/_portal/PanelPruebaIntegracion&Page=Principal&P0=1&P1=eq&P2=%22Ubicacion%22.%22Entidad%20federativa%22&P3=1+AGUASCALIENTES&disabledFilters=0,1,2,3,4";
    		connection =  new URL(path).openConnection();
    		connection.setRequestProperty("user-agent", "Chrome");
    		Scanner scanner = new Scanner(connection.getInputStream());
    		scanner.useDelimiter("\\Z");
    		content = scanner.next();
    		scanner.close();
    	}catch ( Exception ex ) {
    	    ex.printStackTrace();
    	}
    	System.out.println(content);
    	
    	return content;
    }

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String strOrdenDespliegue = request.getParameter("orden").length() == 0 ? "0" : request.getParameter("orden");
		
		long idRol = Long.parseLong(request.getParameter("idRol"));
		long idReporte = Long.parseLong(request.getParameter("idReporte"));
		String nombreDespliegue = request.getParameter("nombre");
		int ordenDespliegue = Integer.parseInt(strOrdenDespliegue);
		String paramOpcionales = request.getParameter("opcionales");
		String paramMandatorios = request.getParameter("mandatorios");
		String paramNulos = request.getParameter("nulos");
		String pagina = request.getParameter("pagina");
		
		PORTALBI_ROLREPORTE newRolConfig = new PORTALBI_ROLREPORTE();
		newRolConfig.setIdRol(idRol);
		newRolConfig.setIdReporte(idReporte);
		newRolConfig.setNombreDespliegue(nombreDespliegue);
		newRolConfig.setOrdenDespliegue(ordenDespliegue);
		newRolConfig.setPagina(pagina);
		newRolConfig.setParametrosMandatorios(paramMandatorios);
		newRolConfig.setParametrosNulos(paramNulos);
		newRolConfig.setParametrosOpcionales(paramOpcionales);
		
		Method_Response result = Implementacion.insert(newRolConfig);
		switch (result.getCode()) {
			case SUCCESS:
				response.sendRedirect("configuracionreportesrol?accion=listar");
				break;
			case DUPLICATE_REGISTER:
			default:
				PrintWriter out = response.getWriter();
				out.println(result.getMessage());
				out.println("</br>");
				return;
		}
		
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		String idRol = request.getParameter("id");
		String idReporte = request.getParameter("idReporte");
		
		PORTALBI_ROLREPORTE newConfigRol = Implementacion.getConfiguration(Long.valueOf(idRol), Long.valueOf(idReporte));		
		request.setAttribute("configuracionReporteRol", newConfigRol);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/editarReportesRol.jsp");
		dispatcher.forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long idRol = Long.parseLong(request.getParameter("idRol"));
		long idReporte = Long.parseLong(request.getParameter("idReporte"));
		String nombreDespliegue = request.getParameter("nombre");
		int ordenDespliegue = Integer.parseInt(request.getParameter("orden"));
		String paramOpcionales = request.getParameter("opcionales");
		String paramMandatorios = request.getParameter("mandatorios");
		String paramNulos = request.getParameter("nulos");
		String pagina = request.getParameter("pagina");
		
		PORTALBI_ROLREPORTE configReporteRol = new PORTALBI_ROLREPORTE();
		configReporteRol.setIdRol(idRol);
		configReporteRol.setIdReporte(idReporte);
		configReporteRol.setNombreDespliegue(nombreDespliegue);
		configReporteRol.setOrdenDespliegue(ordenDespliegue);
		configReporteRol.setPagina(pagina);
		configReporteRol.setParametrosMandatorios(paramMandatorios);
		configReporteRol.setParametrosNulos(paramNulos);
		configReporteRol.setParametrosOpcionales(paramOpcionales);
		boolean estatusUpdate = Implementacion.update(configReporteRol);
		if (estatusUpdate) {
			response.sendRedirect("configuracionreportesrol?accion=listar");
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long idRol = Long.valueOf(request.getParameter("id"));
		Long idReporte = Long.valueOf(request.getParameter("idReporte"));
		System.out.println("Parámetro a borrar " + idRol + " - " + idReporte);
		
		boolean estatusBorrado = Implementacion.delete(idRol, idReporte);
		
		System.out.println("Estatus de borrado " + estatusBorrado);
		response.sendRedirect("configuracionreportesrol?accion=listar");
	}	
}
