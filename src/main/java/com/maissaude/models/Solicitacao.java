package com.maissaude.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Solicitacao implements Serializable{


	private static final long serialVersionUID =1l;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long idR;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Especialidades esp;

	public long getIdR() {
		return idR;
	}

	public void setIdR(long idR) {
		this.idR = idR;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Especialidades getEsp() {
		return esp;
	}

	public void setEsp(Especialidades esp) {
		this.esp = esp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}