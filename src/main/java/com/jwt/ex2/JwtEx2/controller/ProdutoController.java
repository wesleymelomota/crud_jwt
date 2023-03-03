package com.jwt.ex2.JwtEx2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.ex2.JwtEx2.model.Produto;
import com.jwt.ex2.JwtEx2.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	@PostMapping("/criar")
	public Produto criarProduto(@RequestBody Produto produto) {
		service.salvar(produto);
		return produto;
	}
	@GetMapping("/obter-todos")
	public List<Produto> obterTodos(){
		return service.obterTodos();
	}
	@PutMapping("/update")
	public Produto update(@RequestBody Produto produto) {
		return service.update(produto);
	}
	@DeleteMapping("/delete/{id}")
	public String deleteProduto(@PathVariable Integer id) {
		return service.deletar(id);
	}
}
