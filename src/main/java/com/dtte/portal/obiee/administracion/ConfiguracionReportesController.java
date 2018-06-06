package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.ConfigREPORTEImpl;
import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;

public class ConfiguracionReportesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private ConfigREPORTEImpl Implementacion = new ConfigREPORTEImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String actionFromWeb = request.getServletPath();
		System.out.println("actionFromWeb: " + actionFromWeb);

		if (!actionFromWeb.equals("/configuracionreportes"))
			throw new ServletException("Error en el llamado");

		try {
			String accion = request.getParameter("accion");
			System.out.println("accion: " + accion);

			switch (accion) {
			case "new":
				newForm(request, response);
				break;
			case "edit":
				edit(request, response);
				break;
			case "insert":
				insert(request, response);
				break;
			case "delete":
				delete(request, response);
				break;
			case "update":
				update(request, response);
				break;
			case "listar":
			default:
				listConfigurations(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parametro = request.getParameter("parametro");
		String nombre = request.getParameter("nombre");
		PORTALBI_CONFIGREPORTE newReporteConfig = new PORTALBI_CONFIGREPORTE();
		newReporteConfig.setIdConfigreporte(Long.valueOf(request.getParameter("id")));
		newReporteConfig.setParametro(parametro);
		newReporteConfig.setNombre(nombre);
		newReporteConfig.setEstatus(Integer.parseInt(request.getParameter("estatus")));
		newReporteConfig.setPanel(request.getParameter("panel"));
		newReporteConfig.setPath(request.getParameter("path"));
		
		boolean estatusUpdate = Implementacion.update(newReporteConfig);
		if (estatusUpdate) {
			response.sendRedirect("configuracionreportes?accion=listar");
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String identificador = request.getParameter("id");
		System.out.println("Parámetro a borrar " + identificador);
		boolean estatusBorrado = Implementacion.delete(Long.valueOf(identificador));
		System.out.println("Estatus de borrado " + estatusBorrado);
		response.sendRedirect("configuracionreportes?accion=listar");
		
	}

	private void listConfigurations(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<PORTALBI_CONFIGREPORTE> listaConf = Implementacion.listAllConfigReporte();
		request.setAttribute("listaConfiguracionesReportes", listaConf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/configuracionReportes.jsp");
		dispatcher.forward(request, response);
	}

	private void newForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/editarReportes.jsp");
		dispatcher.forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		String idConfigobiee = request.getParameter("id");
		System.out.println("idConfigREPORTE: " + idConfigobiee);

		PORTALBI_CONFIGREPORTE newConfigReporte = Implementacion.getConfiguration(Long.valueOf(idConfigobiee));
		request.setAttribute("configuracionReporte", newConfigReporte);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/editarReportes.jsp");
		dispatcher.forward(request, response);

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parametro = request.getParameter("parametro");
		String nombre = request.getParameter("nombre");

		PORTALBI_CONFIGREPORTE newReporteConfig = new PORTALBI_CONFIGREPORTE();
		int maximoIndice = Implementacion.getMaxId();
		if (maximoIndice > 0) {
			newReporteConfig.setIdConfigreporte(Long.valueOf(maximoIndice + 1));
			newReporteConfig.setParametro(parametro);
			newReporteConfig.setNombre(nombre);
			newReporteConfig.setEstatus(Integer.parseInt(request.getParameter("estatus")));
			newReporteConfig.setPanel(request.getParameter("panel"));
			newReporteConfig.setPath(request.getParameter("path"));
	
			if (Implementacion.insert(newReporteConfig)) {
				response.sendRedirect("configuracionreportes?accion=listar");
			} else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

}
