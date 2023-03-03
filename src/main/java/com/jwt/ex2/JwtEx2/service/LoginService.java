package com.jwt.ex2.JwtEx2.service;

import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.ex2.JwtEx2.dto.DtoLogin;
import com.jwt.ex2.JwtEx2.dto.Sessao;
import com.jwt.ex2.JwtEx2.model.Usuario;
import com.jwt.ex2.JwtEx2.repository.UserRepository;
import com.jwt.ex2.JwtEx2.security.JwtCreator;
import com.jwt.ex2.JwtEx2.security.JWTobject;

import com.jwt.ex2.JwtEx2.config.SecurityConfig;

@Service
public class LoginService {
	@Autowired
	private UserRepository repository;
	
	private PasswordEncoder encoder = new BCryptPasswordEncoder();
	@Autowired
	private SecurityConfig securityConfig;
	
	public Sessao logar(DtoLogin login) {
		
		Usuario usuario = repository.findBynomeusuario(login.getNomeusuario());
		
		if(usuario!=null) {
			Boolean passOk = encoder.matches(login.getSenha(), usuario.getSenha());
			JWTobject token = new JWTobject();
			Sessao sessao = new Sessao();
			if(!passOk) {
				throw new RuntimeErrorException(null, "Senha invalida para o login! " + login.getNomeusuario());
				
			}
		 
			token.setIssuedAT(new Date(System.currentTimeMillis()));
			token.setSubject(usuario.getNomeusuario());
			token.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
			token.setRoles(usuario.getRoles());
			sessao.setLogin(usuario.getNomeusuario());
			sessao.setToken(JwtCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, token));
			return sessao;
		}else {
			throw new RuntimeErrorException(null, "Erro ao fazer Login");
		}
	}
	
	
}
