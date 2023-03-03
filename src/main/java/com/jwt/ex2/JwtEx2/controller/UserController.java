package com.jwt.ex2.JwtEx2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.ex2.JwtEx2.dto.DtoUser;
import com.jwt.ex2.JwtEx2.model.Usuario;
import com.jwt.ex2.JwtEx2.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@PostMapping
	public DtoUser criarUser(@RequestBody Usuario user) {
		return service.salvarUser(user);
	}
	@GetMapping("/obter-todos")
	public List<DtoUser> obterTodos(){
		return service.obterTodos();
	}
	@GetMapping("/users-pag")
	public String paginaUser() {
		return "Olá, Usuario. como vai ?";
	}
}
