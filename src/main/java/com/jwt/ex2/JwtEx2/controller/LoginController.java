package com.jwt.ex2.JwtEx2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.ex2.JwtEx2.dto.DtoLogin;
import com.jwt.ex2.JwtEx2.dto.Sessao;
import com.jwt.ex2.JwtEx2.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService service;
	
	@PostMapping("/login")
	public Sessao login(@RequestBody DtoLogin login) {
		
		return service.logar(login);
	}
}
