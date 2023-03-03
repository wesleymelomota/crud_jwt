package com.jwt.ex2.JwtEx2.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.jwt.ex2.JwtEx2.model.Usuario;

public class DtoUser {
	
	private String nome;
	private String nomeusuario;
	private List<String> roles;
	
	public DtoUser convert(Usuario user) {
		BeanUtils.copyProperties(user, this, "id", "senha");
		return this;
	}
	
	public List<DtoUser> convertList(List<Usuario> users) {
		DtoUser userDto = new DtoUser();
		List<DtoUser> userDtoList = new ArrayList<>();
		users.forEach(u -> {
			userDtoList.add(userDto.convert(u));
		});
		return userDtoList;
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

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}
