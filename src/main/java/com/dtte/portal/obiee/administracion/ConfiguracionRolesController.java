package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.ConfigOBIIEImpl;
import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;

public class ConfiguracionRolesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private ConfigROLImpl Implementacion = new ConfigROLImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String actionFromWeb = request.getServletPath();
		System.out.println("actionFromWeb: " + actionFromWeb);

		if (!actionFromWeb.equals("/configuracionroles"))
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
		String parametroPrueba = "PRESIDENTE";
		response.setContentType("text/html; charset=iso-8859-1");
		PORTALBI_CONFIGROL valor = Implementacion.ObtainRolByName(parametroPrueba);
		PrintWriter out = response.getWriter();
		out.println("Esto es una consulta del objeto Rol basado en el nombre");
		out.println("</br>");
		out.println("Nombre del rol: " + parametroPrueba);
		out.println("Desde el objeto/Nombre: " + valor.getNombre());
		out.println("Desde el objeto/Parametro: " + valor.getParametro());
		out.println("Desde el objeto/ID: " + valor.getIdConfigrol());

	}

	private void newForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/editarRoles.jsp");
		dispatcher.forward(request, response);
	}

	private void listarConfiguraciones(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		java.util.List<PORTALBI_CONFIGROL> listaConf = Implementacion.listAllConfigsRol();
		request.setAttribute("listaConfiguracionesRoles", listaConf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/configuracionRoles.jsp");
		dispatcher.forward(request, response);
	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parametro = request.getParameter("parametro");
		String nombre = request.getParameter("nombre");
		PORTALBI_CONFIGROL newRolConfig = new PORTALBI_CONFIGROL();
		newRolConfig.setParametro(parametro);
		newRolConfig.setNombre(nombre);
		int maximoIndice = Implementacion.getLastCounter();
		if (maximoIndice > 0) {
			newRolConfig.setIdConfigrol(Long.valueOf(maximoIndice + 1));
			if (Implementacion.insert(newRolConfig)) {
				response.sendRedirect("configuracionroles?accion=listar");
			} else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		String idConfigobiee = request.getParameter("id");
		System.out.println("idConfigROL: " + idConfigobiee);		
		PORTALBI_CONFIGROL newConfigRol = Implementacion.getConfiguration(Long.valueOf(idConfigobiee));		
		request.setAttribute("configuracionRol", newConfigRol);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/editarRoles.jsp");
		dispatcher.forward(request, response);

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parametro = request.getParameter("parametro");
		String nombre = request.getParameter("nombre");
		String idConfigRol = request.getParameter("id");
		PORTALBI_CONFIGROL newConfigRol = new PORTALBI_CONFIGROL();
		newConfigRol.setParametro(parametro);
		newConfigRol.setNombre(nombre);
		newConfigRol.setIdConfigrol(Long.valueOf(idConfigRol));
		boolean estatusUpdate = Implementacion.update(newConfigRol);
		if (estatusUpdate) {
			response.sendRedirect("configuracionroles?accion=listar");
		} else {
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String identificador = request.getParameter("id");
		System.out.println("Parámetro a borrar " + identificador);
		boolean estatusBorrado = Implementacion.delete(Long.valueOf(identificador));
		System.out.println("Estatus de borrado " + estatusBorrado);
		response.sendRedirect("configuracionroles?accion=listar");
	}

	/*
	 * private void consultarRol(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { String parametroPrueba = "servidor";
	 * response.setContentType("text/html; charset=iso-8859-1"); String valor =
	 * Implementacion.ObtainValor(parametroPrueba); PrintWriter out =
	 * response.getWriter(); out.println("Esto es una consulta de valor a la DB");
	 * out.println("</br>"); out.println("Valores usando jdbc ");
	 * out.println("Parametro: " + parametroPrueba); out.println("Valor: " + valor);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
