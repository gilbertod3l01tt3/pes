package com.dtte.portal.obiee.model;

public class PORTALBI_CONFIGOBIEE {
	
	private Long idConfigobiee;
    private String parametro;
    private String valor;

    public PORTALBI_CONFIGOBIEE() {
    }

	public PORTALBI_CONFIGOBIEE(Long idConfigobiee, String parametro,
                               String valor) {
        this.idConfigobiee = idConfigobiee;
        this.parametro = parametro;
        this.valor = valor;
    }

    public Long getIdConfigobiee() {
        return idConfigobiee;
    }

    public void setIdConfigobiee(Long idConfigobiee) {
        this.idConfigobiee = idConfigobiee;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
 }