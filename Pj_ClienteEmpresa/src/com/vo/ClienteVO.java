package com.vo;

import java.io.Serializable;

/**
 * VoCliente 
 */
public class ClienteVO extends VO implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idCliente;
	private String sobreNome;
	private String telefoneCelular;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}
	public String getTelefoneCelular() {
		return telefoneCelular;
	}
	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	
}
