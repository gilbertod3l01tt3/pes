package com.dtte.portal.obiee.administracion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dtte.portal.obiee.impl.ConfigREPORTEImpl;
import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.impl.RolREPORTEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.utils.Common;

public class GeneraSelectoresController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	private ConfigREPORTEImpl reporteImpl = new ConfigREPORTEImpl();
	private ConfigROLImpl rolImpl = new ConfigROLImpl();
	private RolREPORTEImpl rolreporteImpl = new RolREPORTEImpl();
	private Common utils = new Common();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType( "text/html; charset=iso-8859-1" );
		PrintWriter out = response.getWriter();
		List<String> Estados = new ArrayList<String>();
		Estados.add("Aguascalientes");
		Estados.add("Baja California");
		Estados.add("Baja California Sur");
		Estados.add("Campeche");
		Estados.add("Chiapas");
		Estados.add("Chihuahua");
		Estados.add("Ciudad De Mexico");
		Estados.add("Coahuila De Zaragoza");
		Estados.add("Colima");
		Estados.add("Durango");
		Estados.add("Guanajuato");
		Estados.add("Guerrero");
		Estados.add("Hidalgo");
		Estados.add("Jalisco");
		Estados.add("Michoaca De Ocampo");
		Estados.add("Morelos");
		Estados.add("Mexico");
		Estados.add("Nayarit");
		Estados.add("Nuevo Leon");
		Estados.add("Oaxaca");
		Estados.add("Puebla");
		Estados.add("Queretaro");
		Estados.add("Quintana Roo");
		Estados.add("San Luis Potosi");
		Estados.add("Sinaloa");
		Estados.add("Sonora");
		Estados.add("Tabasco");
		Estados.add("Tamaulipas");
		Estados.add("Tlaxcala");
		Estados.add("Veracruz Ignacio De Llave");
		Estados.add("Yucatan");
		Estados.add("Zacatecas");
		// Obtengo los datos de la peticion
		String rol = request.getParameter("rol");
		String idreporte = request.getParameter("reporte");
		Long idrol = rolImpl.ObtainRolByName(rol).getIdConfigrol();
		String parametrosmandatorios= rolreporteImpl.getMandatoryParam(idrol,Long.valueOf(idreporte));
		String parametrosopcionales= rolreporteImpl.getOptionalParam(idrol,Long.valueOf(idreporte));
		
		if(parametrosmandatorios == null && parametrosopcionales == null) {
			out.println("<input id=\"consultar\" type=\"button\" class=\"btn btn-primary\" value=\"Consultar\"/>");
		}else {
			if(parametrosmandatorios != null) {
				Map<String, String> mandatorios = utils.getVariablesAndValues(parametrosmandatorios);
				Object[] m = mandatorios.values().toArray();
				for(int i=0;i<m.length;i++){
					
					out.println("<p><strong>"+m[i]+"</strong></p>");
					out.println("<select id="+m[i]+" class=\"js-example-basic-multiple\" multiple=\"multiple\">");
					out.println("<option value=\"0\">Seleccione "+m[i]+"</option>");	
					
					///////////Aquí hay que ir al DWH//////////////
					
					for(int j=0;j<Estados.size();j++) {
						out.println("<option value="+Estados.get(j)+">"+Estados.get(j)+"</option>");
					}
					out.println("</select></br>");	
				}	
			}
			if(parametrosopcionales != null) {
				Map<String, String> opcionales = utils.getVariablesAndValues(parametrosopcionales);
				Object[] o = opcionales.values().toArray();
				for(int k=0;k<o.length;k++){
					
					out.println("<p><strong>"+o[k]+"</strong></p>");
					out.println("<select id="+o[k]+" class=\"js-example-basic-multiple\" multiple=\"multiple\">");
					out.println("<option value=\"0\">Seleccione "+o[k]+" </option>");	
					for(int j=0;j<Estados.size();j++) {
						out.println("<option value="+Estados.get(j)+">"+Estados.get(j)+"</option>");
					}
					out.println("</select></br>");	
				}				
			}
			out.println("<input id=\"consultar\" type=\"button\" class=\"btn btn-primary\" value=\"Consultar\"/>");
		}
	}
}