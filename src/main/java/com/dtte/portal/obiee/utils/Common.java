package com.dtte.portal.obiee.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Common {

	public Map<String, String> getVariablesAndValues(String parameters) {
		System.out.println("Parametros recibidos: " + parameters);
		Map<String, String> mapaDeValores = new HashMap<String, String>();
		String[] separarParametros = parameters.split(",");
		System.out.println("Parametros separados: " + separarParametros[0]);

		for (String parametro : separarParametros) {
			System.out.println("parametroSeparado: " + parametro);
			String[] separarValores = parametro.split("\\|");
			System.out.println("separadosdato longitud: " + separarValores.length);
			System.out.println("separadosdato: " + separarValores[0]);
			System.out.println("separadosdato: " + separarValores[1]);
			mapaDeValores.put(separarValores[0], separarValores[1]);
		}

		System.out.println("Tamaño del mapa: " + mapaDeValores.size());

		return mapaDeValores;

	}

	public StringBuilder buildPlainURL(String url, String port, String user, String password) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(url);
		sb.append(":");
		sb.append(port);
		sb.append("/analytics/saw.dll?dashboard");
		// sb.append("&Action=Navigate");
		sb.append("&NQUser=");
		sb.append(user);
		sb.append("&NQPassword=");
		sb.append(password);
		return sb;
	}

	public String getDinamicURL(Map<String, Object> map)
			throws UnsupportedEncodingException, MalformedURLException, URISyntaxException {
		System.out.println("getDinamicURL");
		StringBuilder urlDin = new StringBuilder();
		// PortalPath
		// urlDin.append("&PortalPath=" + map.get("Path") + map.get("Panel"));
		urlDin.append("&PortalPath=");
		String pathplain = map.get("Path").toString() + map.get("Panel").toString();
		// String pathPanelEncoded = URLEncoder.encode(pathplain, "UTF-8");
		urlDin.append(pathplain);
		map.remove("Path");
		map.remove("Panel");
		// Page
		if (map.containsKey("Page")) {
			urlDin.append("&Page=");
			// urlDin.append(URLEncoder.encode(map.get("Page").toString(), "UTF-8"));
			urlDin.append(map.get("Page"));
			map.remove("Page");
		}
		int cuentaParametros = map.size();
		if (cuentaParametros >= 1) {
			urlDin.append("&P0=");
			urlDin.append(cuentaParametros);
			urlDin.append("&P1=eq");
			// Imprimimos el Map con un Iterador
			Iterator it = map.keySet().iterator();
			int j = 2;
			while (it.hasNext()) {
				String key = (String) it.next();
				String value = (String) map.get(key);

				System.out.println("Clave: " + key + " -> Valor: " + value);
				urlDin.append("&P" + j + "=");
				urlDin.append(key);
				urlDin.append("&P" + (j + 1) + "=");
				String[] vectorCheck = value.split(",");
				if (vectorCheck.length > 0) {
					urlDin.append(vectorCheck.length + "+");
					String my_new_str = value.replace(",", "+");
					urlDin.append(my_new_str);
					j = j + 2;
				} else {
					urlDin.append(value);
					j = j + 2;
				}
			}
		}
		return urlDin.toString();
	}

}
