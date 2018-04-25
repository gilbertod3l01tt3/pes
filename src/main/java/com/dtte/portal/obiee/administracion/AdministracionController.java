package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.ConfigOBIIEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGOBIEE;

public class AdministracionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			response.setContentType("text/html; charset=iso-8859-1");
			PrintWriter out = response.getWriter();
			out.println("Esto es un renglon");
			out.println("</br>");
			// out.println("Valores de tabla: "+configMapper.getConfig()); try {

			PORTALBI_CONFIGOBIEE quees = new ConfigOBIIEImpl().listAllConfigs().get(1);
			out.println("Valores usando jdbc ");
			out.println("Renglon 1 / ID: " + quees.getIdConfigobiee());
			out.println("Renglon 1 / Parametro: " + quees.getParametro().toString());
			out.println("Renglon 1 / Valor: " + quees.getValor());
		} catch ( SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getServletInfo() {
		return "Administracion Servlet";
	}
}
