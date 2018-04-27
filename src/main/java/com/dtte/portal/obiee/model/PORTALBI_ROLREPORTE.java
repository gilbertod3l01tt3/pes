package com.dtte.portal.obiee.model;

public class PORTALBI_ROLREPORTE {
	private Long idConfigrol;
	private Long idConfigreporte;
    private String nombredespliegue;
    private int ordendespliegue;
    private String parametromandatorio;
    private String parametroopcional;
    private String parametronulo;
    private String pagina;

    public PORTALBI_ROLREPORTE() {
    }
    
	public PORTALBI_ROLREPORTE(Long idConfigrol, Long idConfigreporte, String nombredespliegue, int ordendespliegue,
			String parametromandatorio, String parametroopcional, String parametronulo, String pagina) {
		super();
		this.idConfigrol = idConfigrol;
		this.idConfigreporte = idConfigreporte;
		this.nombredespliegue = nombredespliegue;
		this.ordendespliegue = ordendespliegue;
		this.parametromandatorio = parametromandatorio;
		this.parametroopcional = parametroopcional;
		this.parametronulo = parametronulo;
		this.pagina = pagina;
	}

	/**
	 * @return the idConfigrol
	 */
	public Long getIdConfigrol() {
		return idConfigrol;
	}

	/**
	 * @param idConfigrol the idConfigrol to set
	 */
	public void setIdConfigrol(Long idConfigrol) {
		this.idConfigrol = idConfigrol;
	}

	/**
	 * @return the idConfigreporte
	 */
	public Long getIdConfigreporte() {
		return idConfigreporte;
	}

	/**
	 * @param idConfigreporte the idConfigreporte to set
	 */
	public void setIdConfigreporte(Long idConfigreporte) {
		this.idConfigreporte = idConfigreporte;
	}

	/**
	 * @return the nombredespliegue
	 */
	public String getNombredespliegue() {
		return nombredespliegue;
	}

	/**
	 * @param nombredespliegue the nombredespliegue to set
	 */
	public void setNombredespliegue(String nombredespliegue) {
		this.nombredespliegue = nombredespliegue;
	}

	/**
	 * @return the ordendespliegue
	 */
	public int getOrdendespliegue() {
		return ordendespliegue;
	}

	/**
	 * @param ordendespliegue the ordendespliegue to set
	 */
	public void setOrdendespliegue(int ordendespliegue) {
		this.ordendespliegue = ordendespliegue;
	}

	/**
	 * @return the parametromandatorio
	 */
	public String getParametromandatorio() {
		return parametromandatorio;
	}

	/**
	 * @param parametromandatorio the parametromandatorio to set
	 */
	public void setParametromandatorio(String parametromandatorio) {
		this.parametromandatorio = parametromandatorio;
	}

	/**
	 * @return the parametroopcional
	 */
	public String getParametroopcional() {
		return parametroopcional;
	}

	/**
	 * @param parametroopcional the parametroopcional to set
	 */
	public void setParametroopcional(String parametroopcional) {
		this.parametroopcional = parametroopcional;
	}

	/**
	 * @return the parametronulo
	 */
	public String getParametronulo() {
		return parametronulo;
	}

	/**
	 * @param parametronulo the parametronulo to set
	 */
	public void setParametronulo(String parametronulo) {
		this.parametronulo = parametronulo;
	}

	/**
	 * @return the pagina
	 */
	public String getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	
}
