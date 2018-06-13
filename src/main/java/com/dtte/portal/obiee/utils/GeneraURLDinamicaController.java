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

import com.dtte.portal.obiee.utils.Utils;
import com.dtte.portal.obiee.impl.ConfigOBIIEImpl;
import com.dtte.portal.obiee.impl.ConfigREPORTEImpl;
import com.dtte.portal.obiee.impl.ConfigROLImpl;
import com.dtte.portal.obiee.impl.RolREPORTEImpl;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGREPORTE;
import com.dtte.portal.obiee.model.PORTALBI_CONFIGROL;
import com.dtte.portal.obiee.model.PORTALBI_ROLREPORTE;
import com.dtte.portal.obiee.utils.GeneradorURL;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeneraURLDinamicaController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private RolREPORTEImpl rolreporteImpl = new RolREPORTEImpl();
	private ConfigROLImpl rolImpl = new ConfigROLImpl();
	private Common utils = new Common();
	private ConfigOBIIEImpl configImpl = new ConfigOBIIEImpl();
	private RolREPORTEImpl configReporteRolImpl = new RolREPORTEImpl();
	private ConfigREPORTEImpl configReporte = new ConfigREPORTEImpl();

	public GeneradorURL generadorURL = new GeneradorURL();
    private HashMap<String, Object> mapaEstaticoBase = new HashMap<>();
    private HashMap<String, Object> mapaDinamicoBase = new HashMap<>();
    
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
		}

		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		// Pasando json string a mapa
		map = mapper.readValue(json1, new TypeReference<Map<String, String>>() {
		});
		
		if (!(map.containsKey("rol") && (map.containsKey("reporte"))))
			throw new IOException("Parametros mandatorios no se encuentran en el request");

		// Obteniendo el Rol a configurar
		String nombre = (String) map.get("rol");
		PORTALBI_CONFIGROL rol = rolImpl.ObtainRolByName(nombre);

		// Obteniendo los parámetros del Reporte
		String idreporte = (String) map.get("reporte");
		String parametrosmandatorios = rolreporteImpl.getMandatoryParam(rol.getIdConfigrol(),
				Long.valueOf(idreporte));
		if(parametrosmandatorios != null) {
		String[] mandatorios = parametrosmandatorios.split(",");
		// Validar si los parámetros mandatorios se encuentran presentes
			for (int i = 0; i < mandatorios.length; i++) {
				String[] separarValores = mandatorios[i].split("\\|");
				if (!map.containsKey(separarValores[0])) {
					throw new IOException("Parametros mandatorios no se encuentran en el request");
				}
				String valor= map.get(separarValores[0]).toString().toUpperCase();
				
				mapaDinamicoBase.put(separarValores[0],valor);
			}
		}
		// Agregando la página si existe al mapa de variables
		PORTALBI_ROLREPORTE pagina = configReporteRolImpl.getConfiguration(rol.getIdConfigrol(),
				Long.valueOf(idreporte));
		if (pagina != null && pagina.getPagina() != null) {
			mapaDinamicoBase.put(Utils.KEY_PAGE, pagina.getPagina());
		}
		// Trayendo configuración de reporte, path y panel
		PORTALBI_CONFIGREPORTE reporteExtraido = configReporte.getConfiguration(Long.valueOf(idreporte));
			
		mapaDinamicoBase.put(Utils.KEY_PATH, reporteExtraido.getPath());
		mapaDinamicoBase.put(Utils.KEY_PANEL, reporteExtraido.getPanel());
		
		// Traer los datos de conexión del Rol
		Map<String, String> variablesPorReporte = utils.getVariablesAndValues(rol.getParametro());
		
		mapaEstaticoBase.put(Utils.KEY_HOST, configImpl.ObtainValue("servidor"));
        mapaEstaticoBase.put(Utils.KEY_PORT, configImpl.ObtainValue("puerto"));
        mapaEstaticoBase.put(Utils.KEY_USER, variablesPorReporte.get("NQUser"));
        mapaEstaticoBase.put(Utils.KEY_PASS, variablesPorReporte.get("NQPassword"));
       
		String OBIEEAction = configImpl.ObtainValue("ActionOBIEE");
		//urlEstatica.append("&Action=" + OBIEEAction);
		mapaEstaticoBase.put(Utils.KEY_ACTION, OBIEEAction);
		if (map.containsKey("\"Ubicacion\".\"Entidad federativa\"")) {
			mapaEstaticoBase.put(Utils.KEY_DISABLED_FILTERS, "0");
		}
		
		StringBuilder resultadoEstatico = generadorURL.GeneradorURLEstatica(mapaEstaticoBase);
        String resultadoDinamico = generadorURL.GeneradorURLDinamica(mapaDinamicoBase);
        String resultadoFinal = generadorURL.ConcatenadorEncoder(resultadoEstatico, resultadoDinamico);
        mapaEstaticoBase.clear();
        mapaDinamicoBase.clear();
        System.out.println("Url final: "+ resultadoFinal);
        out.println(resultadoFinal);
	}

	@Override
	public String getServletInfo() {
		return "Servlet que construye la URL dinámicamente para consumir al OBIEE";
	}

}
