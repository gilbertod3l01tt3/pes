package com.dtte.portal.obiee.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import com.dtte.portal.obiee.model.SelectorResult;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		List<Object> lista = new ArrayList<Object>();
		
        ObjectMapper mapper = new ObjectMapper(); 
        response.setContentType("application/json"); 
        
		List<String> Estados = new ArrayList<String>();
		Estados.add("Aguascalientes");
		Estados.add("Baja California");
		Estados.add("Baja California Sur");
		Estados.add("Campeche");
		Estados.add("Chiapas");
		Estados.add("Chihuahua");
		Estados.add("Ciudad De México");
		Estados.add("Coahuila De Zaragoza");
		Estados.add("Colima");
		Estados.add("Durango");
		Estados.add("Guanajuato");
		Estados.add("Guerrero");
		Estados.add("Hidalgo");
		Estados.add("Jalisco");
		Estados.add("México");
		Estados.add("Michoacán De Ocampo");
		Estados.add("Morelos");
		Estados.add("Nayarit");
		Estados.add("Nuevo Leon");
		Estados.add("Oaxaca");
		Estados.add("Puebla");
		Estados.add("Querétaro");
		Estados.add("Quintana Roo");
		Estados.add("San Luis Potosí");
		Estados.add("Sinaloa");
		Estados.add("Sonora");
		Estados.add("Tabasco");
		Estados.add("Tamaulipas");
		Estados.add("Tlaxcala");
		Estados.add("Veracruz De Ignacio De La Llave");
		Estados.add("Yucatán");
		Estados.add("Zacatecas");
		
		// Se obtienen los datos de la peticion
		String rol = request.getParameter("rol");
		String idreporte = request.getParameter("reporte");
		
		// Se obtienen los parametros mandatorios y opciones del reporte seleccionado
		Long idrol = rolImpl.ObtainRolByName(rol).getIdConfigrol();
		String parametrosmandatorios= rolreporteImpl.getMandatoryParam(idrol,Long.valueOf(idreporte));
		String parametrosopcionales= rolreporteImpl.getOptionalParam(idrol,Long.valueOf(idreporte));
		
		
		if(parametrosmandatorios == null && parametrosopcionales == null) {
			//out.println("<input id=\"consultar\" type=\"button\" class=\"btn btn-primary\" value=\"Consultar\"/>");
			// Se envía la respuesta al cliente
	        //mapper.writeValue(response.getOutputStream(), reportes);
		}else {
			if(parametrosmandatorios != null) {
				Map<String, String> mandatorios = utils.getVariablesAndValues(parametrosmandatorios);
				lista.add(mandatorios);
			}
			if(parametrosopcionales != null) {
				Map<String, String> opcionales = utils.getVariablesAndValues(parametrosopcionales);
				lista.add(opcionales);
					
			}
			
			mapper.writeValue(response.getOutputStream(), lista);
		}
	}
}