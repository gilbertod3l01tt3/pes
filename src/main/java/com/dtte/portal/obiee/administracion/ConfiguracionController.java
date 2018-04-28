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
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;

public class ConfiguracionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private ConfigOBIIEImpl Implementacion = new ConfigOBIIEImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String actionFromWeb = request.getServletPath();
		System.out.println("actionFromWeb: " + actionFromWeb);

		if (!actionFromWeb.equals("/configuracion"))
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
			case "listar":
				listarConfiguraciones(request, response);
				break;
			case "consultar":
				consultarParametro(request, response);
				break;
			default:
				listarConfiguraciones(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void consultarParametro(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String parametroPrueba = "servidor";
		response.setContentType("text/html; charset=iso-8859-1");
		String valor = Implementacion.ObtainValue(parametroPrueba);
		PrintWriter out = response.getWriter();
		out.println("Esto es una consulta de valor a la DB");
		out.println("</br>");
		out.println("Valores usando jdbc ");
		out.println("Parametro: " + parametroPrueba);
		out.println("Valor: " + valor);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String identificador = request.getParameter("id");
		System.out.println("Parámetro a borrar " + identificador);
		boolean estatusBorrado = Implementacion.delete(Long.valueOf(identificador));
		System.out.println("Estatus de borrado " + estatusBorrado);
		response.sendRedirect("configuracion?accion=listar");
	}

	private void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		String idConfigobiee = request.getParameter("id");
		System.out.println("idConfigobiee: " + idConfigobiee);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/EditarConfiguracionGeneral.jsp");
		PORTALBI_CONFIGOBIEE newConfig = Implementacion.getConfiguration(Long.valueOf(idConfigobiee));
		request.setAttribute("configuracion", newConfig);
		dispatcher.forward(request, response);

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parametro = request.getParameter("parametro");
		String valor = request.getParameter("valor");
		PORTALBI_CONFIGOBIEE newConfig = new PORTALBI_CONFIGOBIEE();
		newConfig.setParametro(parametro);
		newConfig.setValor(valor);
		int maximoIndice = Implementacion.getLastCounter();
		if (maximoIndice > 0) {
			newConfig.setIdConfigobiee(Long.valueOf(maximoIndice + 1));
			if (Implementacion.insert(newConfig)) {
				response.sendRedirect("configuracion?accion=listar");
			} else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		}
		request.getRequestDispatcher("/error.jsp").forward(request, response);
	}

	private void newForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/EditarConfiguracionGeneral.jsp");
		dispatcher.forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String parametro = request.getParameter("parametro");
		String valor = request.getParameter("valor");
		String idConfigobiee = request.getParameter("id");
		PORTALBI_CONFIGOBIEE newConfig = new PORTALBI_CONFIGOBIEE();
		newConfig.setParametro(parametro);
		newConfig.setValor(valor);
		newConfig.setIdConfigobiee(Long.valueOf(idConfigobiee));
		Implementacion.update(newConfig);
		response.sendRedirect("configuracion?accion=listar");
	}

	private void listarConfiguraciones(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		java.util.List<PORTALBI_CONFIGOBIEE> listaConf = Implementacion.listAllConfigs();
		request.setAttribute("listaConfiguraciones", listaConf);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/administracion/configuracionGeneral.jsp");
		dispatcher.forward(request, response);
	}

}
