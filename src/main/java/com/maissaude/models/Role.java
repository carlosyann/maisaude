package com.maissaude.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{
	

	@Id
	public String nome_role;
	
	@ManyToMany(mappedBy = "role")
	public List<Usuario> usuario;

	public String getNome_role() {
		return nome_role;
	}
	
	public void setNome_role(String nome_role) {
		this.nome_role = nome_role;
	}
	@Override
	public String getAuthority() {
		return this.nome_role;
	}
}
