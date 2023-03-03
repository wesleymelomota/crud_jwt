package com.jwt.ex2.JwtEx2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.ex2.JwtEx2.model.Produto;
import com.jwt.ex2.JwtEx2.repository.ProdutoRepository;

@Service
public class ProdutoService {
	 @Autowired
	 private ProdutoRepository repository;
	 
	 public void salvar(Produto produto) {
		 repository.save(produto);
	 }
	 public List<Produto> obterTodos(){
		 return repository.findAll();
	 }
	 public Produto update(Produto produto) {
		 if(produto.getId() != null) {
			 repository.save(produto);
			 return produto;
		 }else {
			 throw new RuntimeException("ERRO ao atualizar");
		 }
		 
	 }
	 public String deletar(Integer id) {
		 repository.deleteById(id);
		 return "Produto Excluido";
	 }
}
