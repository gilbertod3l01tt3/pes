package com.dtte.portal.obiee.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dtte.portal.obiee.impl.ConfigOBIIEImpl;
import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.impl.RolREPORTEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneraURLDinamicaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RolREPORTEImpl rolreporteImpl = new RolREPORTEImpl();
	private ConfigROLImpl rolImpl = new ConfigROLImpl();
	private Common utils = new Common();
	private ConfigOBIIEImpl configImpl = new ConfigOBIIEImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=iso-8859-1");
		PrintWriter out = response.getWriter();

		// Obteniendo parámetros desde el json
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String json1 = "";
		if (br != null) {
			json1 = br.readLine();
			System.out.println(json1);
		}

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// Pasando json string a mapa
		map = mapper.readValue(json1, new TypeReference<Map<String, String>>() {
		});
		System.out.println(map);
		if (!(map.containsKey("rol") && (map.containsKey("reporte"))))
			throw new IOException("Parametros mandatorios no se encuentran en el request");

		// Obteniendo el Rol a configurar
		// String nombre = request.getParameter("rol");
		String nombre = (String) map.get("rol");
		System.out.println("Obteniendo configuracion: " + nombre);
		PORTALBI_CONFIGROL rol = rolImpl.ObtainRolByName(nombre);
		System.out.println("Obteniendo datos desde la base: " + rol.getNombre());
		System.out.println("Obteniendo datos desde la base/parametro: " + rol.getParametro());

		// Obteniendo los parámetros del Reporte
		// String idreporte = request.getParameter("reporte");
		String idreporte = (String) map.get("reporte");
		String[] parametrosmandatorios = rolreporteImpl.getMandatoryParam(rol.getIdConfigrol(),
				Long.valueOf(idreporte));
		// Validar si los parámetros mandatorios se encuentran presentes
		System.out.println("Los parametros mandatorios son: ");
		for (int i = 0; i < parametrosmandatorios.length; i++) {
			System.out.println(parametrosmandatorios[i]);
			if (!map.containsKey(parametrosmandatorios[i])) {
				throw new IOException("Parametros mandatorios no se encuentran en el request");
			}
		}

		// Traer los datos de conexión del Rol
		Map<String, String> variablesPorReporte = utils.getVariablesAndValues(rol.getParametro());
		StringBuilder urlEstatica = utils.buildPlainURL(configImpl.ObtainValue("servidor"),
				configImpl.ObtainValue("PUERTO"), variablesPorReporte.get("NQUser"),
				variablesPorReporte.get("NQPassword"));
		System.out.println("URL estatica: " + urlEstatica.toString());
		// Limpiando el mapa
		map.remove("rol");
		map.remove("reporte");

		// Construyendo la url dinamica
		String urlDinamica = utils.getDinamicURL(map);
		System.out.println("URL dinamica: " + urlDinamica);
		urlEstatica.append(urlDinamica);
		out.println(urlEstatica);
	}

	@Override
	public String getServletInfo() {
		return "Servlet que construye la URL dinámicamente para consumir al OBIEE";
	}

}
