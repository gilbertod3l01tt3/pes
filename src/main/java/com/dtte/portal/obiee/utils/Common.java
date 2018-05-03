package com.dtte.portal.obiee.utils;

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
		sb.append("&Action=Print");
		sb.append("&NQUser=");
		sb.append(user);
		sb.append("&NQPassword=");
		sb.append(password);
		return sb;
	}

}
