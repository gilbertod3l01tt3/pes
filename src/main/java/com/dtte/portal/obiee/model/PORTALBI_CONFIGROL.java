package com.dtte.portal.obiee.model;

public class PORTALBI_CONFIGROL {

	public PORTALBI_CONFIGROL() {

	}
	
	public PORTALBI_CONFIGROL(Long idConfigrol, String parametro,
            String nombre ) {
		this.idConfigrol=idConfigrol;
		this.parametro=parametro;
		this.nombre=nombre;
	}
	
	private Long idConfigrol;
	private String parametro;
	private String nombre;

	public Long getIdConfigrol() {
		return idConfigrol;
	}

	public void setIdConfigrol(Long idConfigrol) {
		this.idConfigrol = idConfigrol;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}