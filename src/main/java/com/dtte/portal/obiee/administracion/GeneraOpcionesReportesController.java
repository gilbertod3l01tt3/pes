package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.impl.RolREPORTEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;

public class GeneraOpcionesReportesController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	private RolREPORTEImpl reporteImpl = new RolREPORTEImpl();
	private ConfigROLImpl rolImpl = new ConfigROLImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();

		// Obtengo los datos de la peticion
		String nombre = request.getParameter("rol");
		PORTALBI_CONFIGROL rol= rolImpl.ObtainRolByName(nombre);
		List<PORTALBI_ROLREPORTE> reportes = reporteImpl.getReportesFromRol(rol.getIdConfigrol());
		
		out.println("<p><strong>Seleccione el reporte que desea visualizar.</strong></p>");
		out.println("<select id=\"option\">");			
		out.println("<option value=\"0\">Seleccione una opci&oacute;n</option>");
		for(int i=0; i<reportes.size(); i++){
			out.println("<option value="+reportes.get(i).getIdConfigreporte()+">"+reportes.get(i).getNombredespliegue()+"</option>");
		}
		out.println("</select>");
		
		out.println("<input type=\"button\" class=\"btn btn-primary\" onclick=\"setURL()\" value=\"Consultar\"/>");
	}

	@Override
	public String getServletInfo() {
		return "Administracion Servlet";
	}
}