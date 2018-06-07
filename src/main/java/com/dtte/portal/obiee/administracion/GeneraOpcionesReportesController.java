package com.dtte.portal.obiee.administracion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.fasterxml.jackson.databind.ObjectMapper;

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
		/*response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();

		// Obtengo los datos de la peticion
		String nombre = request.getParameter("rol");
		PORTALBI_CONFIGROL rol= rolImpl.ObtainRolByName(nombre);
		List<PORTALBI_ROLREPORTE> reportes = reporteImpl.getReportesFromRol(rol.getIdConfigrol());
		
		out.println("<p><strong>Reporte a visualizar:</strong></p>");
		out.println("<select id=\"option\">");			
		out.println("<option value=\"0\">Seleccione una opci&oacute;n</option>");
		for(int i=0; i<reportes.size(); i++){
			out.println("<option value="+reportes.get(i).getIdReporte()+">"+reportes.get(i).getNombreDespliegue()+"</option>");
		}
		out.println("</select></br>");		*/
		  // 1. get received JSON data from request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String json = "";
        if(br != null){
            json = br.readLine();
        }
 
        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();
 
        // 3. Convert received JSON to Article
        //Article article = mapper.readValue(json, Article.class);
 
        // 4. Set response type to JSON
        response.setContentType("application/json");            
 
        // 5. Add article to List<Article>
        String nombre = request.getParameter("rol");
		PORTALBI_CONFIGROL rol= rolImpl.ObtainRolByName(nombre);
		List<PORTALBI_ROLREPORTE> reportes = reporteImpl.getReportesFromRol(rol.getIdConfigrol());
		
 
        // 6. Send List<Article> as JSON to client
        mapper.writeValue(response.getOutputStream(), reportes);
    }
}
