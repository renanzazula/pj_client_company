package com.vo;

import java.io.Serializable;

/**
 * Vo empresa
 */
@SuppressWarnings("serial")
public class EmpresaVO extends VO implements Serializable {
	
	private int idEmpresa;
	private String Telfax;
	private int idCliente;
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getTelfax() {
		return Telfax;
	}
	public void setTelfax(String telfax) {
		Telfax = telfax;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
}
