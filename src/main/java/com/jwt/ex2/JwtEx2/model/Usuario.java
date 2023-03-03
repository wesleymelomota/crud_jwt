package com.jwt.ex2.JwtEx2.model;

import java.util.ArrayList;

import java.util.List;

/*
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;*/
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "tab_user")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Integer id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(name = "nomeusuario", length = 20, nullable = false, unique = true)
	private String nomeusuario;
	
	@Column(length = 100, nullable = false)
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "tab_user_roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "id_roles")
	private List<String> roles = new ArrayList<>();
	
	public Usuario() {}
	
	public Usuario(String nomeusuario) {
		this.nomeusuario = nomeusuario;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeusuario() {
		return nomeusuario;
	}

	public void setNomeusuario(String nomeusuario) {
		this.nomeusuario = nomeusuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	};
	
	
	
}
