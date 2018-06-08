package com.dtte.portal.obiee.utils;

import java.io.IOException;
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
		//  Se crea un objectmapper
        ObjectMapper mapper = new ObjectMapper(); 
        response.setContentType("application/json");            
 
        // Se obtienen datos de la peticion
        String nombre = request.getParameter("rol");
        
        // Se obtienen los reportes asociados al rol
		PORTALBI_CONFIGROL rol= rolImpl.ObtainRolByName(nombre);
		List<PORTALBI_ROLREPORTE> reportes = reporteImpl.getReportesFromRol(rol.getIdConfigrol());
				
        // Se envía la respuesta al cliente
        mapper.writeValue(response.getOutputStream(), reportes);
        
        
    }
}
