package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.ConfigREPORTEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;

public class GeneraSelectoresController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	private ConfigREPORTEImpl reporteImpl = new ConfigREPORTEImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();

		// Obtengo los datos de la peticion
		String idreporte = request.getParameter("reporte");
		String[] parametros= reporteImpl.getParametersById(Long.valueOf(idreporte));
		if(parametros == null) {
			out.println("<input id=\"consultar\" type=\"button\" class=\"btn btn-primary\" value=\"Consultar\"/>");
		}else {
		for(int i=0; i<parametros.length; i++){
			out.println("<p><strong>"+parametros[i]+"</strong></p>");
			out.println("<select id="+parametros[i]+" class=\"js-example-basic-multiple\" multiple=\"multiple\">");
		
				out.println("<option value=\"0\">Seleccione estado</option>");
				out.println("<option value=\"Aguascalientes\">Aguascalientes</option>");
				out.println("<option value=\"Baja California\">Baja California</option>");
				out.println("<option value=\"Puebla\">Puebla</option>");
				out.println("<option value=\"Tlaxcala\">Tlaxcala</option>");				
			
			out.println("</select></br>");	
		}	
		out.println("<input id=\"consultar\" type=\"button\" class=\"btn btn-primary\" value=\"Consultar\"/>");
		}
	}
}