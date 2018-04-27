package com.dtte.portal.obiee.model;

public class PORTALBI_CONFIGREPORTE {
	private Long idConfigreporte;
    private String nombre;
    private int estatus;
    private String parametro;
    private String path;
    private String panel;

    public PORTALBI_CONFIGREPORTE() {
    }

	public PORTALBI_CONFIGREPORTE(Long idConfigreporte, String nombre, int estatus, String parametro, String path,
			String panel) {
		super();
		this.idConfigreporte = idConfigreporte;
		this.nombre = nombre;
		this.estatus = estatus;
		this.parametro = parametro;
		this.path = path;
		this.panel = panel;
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
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the estatus
	 */
	public int getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}

	/**
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the panel
	 */
	public String getPanel() {
		return panel;
	}

	/**
	 * @param panel the panel to set
	 */
	public void setPanel(String panel) {
		this.panel = panel;
	}
    
	
	
}
