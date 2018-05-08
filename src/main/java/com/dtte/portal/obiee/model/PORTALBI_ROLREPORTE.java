package com.dtte.portal.obiee.model;

public class PORTALBI_ROLREPORTE {
	private Long idRol;
	private Long idReporte;
	private String nombreDespliegue;
	private int ordenDespliegue;
	private String parametrosMandatorios;
	private String parametrosOpcionales;
	private String parametrosNulos;
	private String pagina;
	
	/**
	 * Constructor.
	 */
	public PORTALBI_ROLREPORTE() {

	}

	/**
	 * Constructor.
	 * @param idRol
	 * @param idReporte
	 * @param nombreDespliegue
	 * @param ordenDespliegue
	 * @param parametrosMandatorios
	 * @param parametrosOpcionales
	 * @param parametrosNulos
	 * @param pagina
	 */
	public PORTALBI_ROLREPORTE(Long idRol, Long idReporte, String nombreDespliegue, int ordenDespliegue,
			String parametrosMandatorios, String parametrosOpcionales, String parametrosNulos, String pagina) {
		super();
		this.idRol = idRol;
		this.idReporte = idReporte;
		this.nombreDespliegue = nombreDespliegue;
		this.ordenDespliegue = ordenDespliegue;
		this.parametrosMandatorios = parametrosMandatorios;
		this.parametrosOpcionales = parametrosOpcionales;
		this.parametrosNulos = parametrosNulos;
		this.pagina = pagina;
	}

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public String getNombreDespliegue() {
		return nombreDespliegue;
	}

	public void setNombreDespliegue(String nombreDespliegue) {
		this.nombreDespliegue = nombreDespliegue;
	}

	public int getOrdenDespliegue() {
		return ordenDespliegue;
	}

	public void setOrdenDespliegue(int ordenDespliegue) {
		this.ordenDespliegue = ordenDespliegue;
	}

	public String getParametrosMandatorios() {
		return parametrosMandatorios;
	}

	public void setParametrosMandatorios(String parametrosMandatorios) {
		this.parametrosMandatorios = parametrosMandatorios;
	}

	public String getParametrosOpcionales() {
		return parametrosOpcionales;
	}

	public void setParametrosOpcionales(String parametrosOpcionales) {
		this.parametrosOpcionales = parametrosOpcionales;
	}

	public String getParametrosNulos() {
		return parametrosNulos;
	}

	public void setParametrosNulos(String parametrosNulos) {
		this.parametrosNulos = parametrosNulos;
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
		
}
