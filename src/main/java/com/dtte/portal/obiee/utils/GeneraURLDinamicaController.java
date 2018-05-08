package com.dtte.portal.obiee.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dtte.portal.obiee.impl.ConfigOBIIEImpl;
import com.dtte.portal.obiee.impl.ConfigREPORTEImpl;
import com.dtte.portal.obiee.impl.ConfigREPORTEROLImpl;
import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.impl.RolREPORTEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTEROL;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneraURLDinamicaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RolREPORTEImpl rolreporteImpl = new RolREPORTEImpl();
	private ConfigROLImpl rolImpl = new ConfigROLImpl();
	private Common utils = new Common();
	private ConfigOBIIEImpl configImpl = new ConfigOBIIEImpl();
	private ConfigREPORTEROLImpl configReporteRolImpl = new ConfigREPORTEROLImpl();
	private ConfigREPORTEImpl configReporte = new ConfigREPORTEImpl();

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
		String nombre = (String) map.get("rol");
		System.out.println("Obteniendo configuracion: " + nombre);
		PORTALBI_CONFIGROL rol = rolImpl.ObtainRolByName(nombre);
		// System.out.println("Obteniendo datos desde la base: " + rol.getNombre());
		// System.out.println("Obteniendo datos desde la base/parametro: " +

		// Obteniendo los parámetros del Reporte
		String idreporte = (String) map.get("reporte");
		String[] parametrosmandatorios = rolreporteImpl.getMandatoryParam(rol.getIdConfigrol(),
				Long.valueOf(idreporte));
		// Validar si los parámetros mandatorios se encuentran presentes
		System.out.println("Los parametros mandatorios son: ");
		for (int i = 0; i < parametrosmandatorios.length; i++) {
			String[] separarValores = parametrosmandatorios[i].split("\\|");
			System.out.println(parametrosmandatorios[i]);
			System.out.println(separarValores);
			if (!map.containsKey(separarValores[0])) {
				throw new IOException("Parametros mandatorios no se encuentran en el request");
			}
		}

		// Agregando la página si existe al mapa de variables
		PORTALBI_CONFIGREPORTEROL pagina = configReporteRolImpl.getConfiguration(rol.getIdConfigrol(),
				Long.valueOf(idreporte));
		if (!pagina.getPagina().equals(null)) {
			map.put("Page", pagina.getPagina());
		}

		// Trayendo configuración de reporte, path y panel
		PORTALBI_CONFIGREPORTE reporteExtraido = configReporte.getConfiguration(Long.valueOf(idreporte));
		
		map.put("Path", reporteExtraido.getPath());
		map.put("Panel", reporteExtraido.getPanel());
		// Limpiando el mapa
		map.remove("rol");
		map.remove("reporte");
	
		// Traer los datos de conexión del Rol
		Map<String, String> variablesPorReporte = utils.getVariablesAndValues(rol.getParametro());
		StringBuilder urlEstatica = utils.buildPlainURL(configImpl.ObtainValue("servidor"),
				configImpl.ObtainValue("PUERTO"), variablesPorReporte.get("NQUser"),
				variablesPorReporte.get("NQPassword"));
		System.out.println("URL estatica: " + urlEstatica.toString());
		String OBIEEAction = configImpl.ObtainValue("ActionOBIEE");
		System.out.println("Action configurada: " + OBIEEAction);
		urlEstatica.append("&Action=" + OBIEEAction);
		// Construyendo la url dinamica
		String urlDinamica;
		try {
			urlDinamica = utils.getDinamicURL(map);
			System.out.println("URL dinamica: " + urlDinamica);
			urlEstatica.append(urlDinamica);
		} catch (URISyntaxException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("URL total: " + urlEstatica);
		URL url= new URL(urlEstatica.toString());
		String encodedString="";
		try {
			URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
			 encodedString=uri.toASCIIString();
			 System.out.println("URL total encodeada: " + encodedString);
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		out.println(encodedString);
	}

	@Override
	public String getServletInfo() {
		return "Servlet que construye la URL dinámicamente para consumir al OBIEE";
	}

}
