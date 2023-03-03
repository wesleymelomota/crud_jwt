package com.jwt.ex2.JwtEx2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.ex2.JwtEx2.model.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Integer>{
	public Usuario findBynomeusuario(String nome);
}
