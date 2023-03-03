package com.jwt.ex2.JwtEx2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.ex2.JwtEx2.dto.DtoUser;
import com.jwt.ex2.JwtEx2.model.Usuario;
import com.jwt.ex2.JwtEx2.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public DtoUser salvarUser(Usuario usuario) {
		DtoUser userDto = new DtoUser();
		String pass = usuario.getSenha();
		usuario.setSenha(encoder.encode(pass));
		repository.save(usuario);
		userDto.convert(usuario);
		return userDto;
	}
	public List<DtoUser> obterTodos(){
		DtoUser userDto = new DtoUser();
		return userDto.convertList(repository.findAll());
	}
}
