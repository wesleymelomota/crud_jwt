package com.jwt.ex2.JwtEx2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwt.ex2.JwtEx2.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	public Produto findBynome(String nome);
	public Produto findByid(Integer id);
}
