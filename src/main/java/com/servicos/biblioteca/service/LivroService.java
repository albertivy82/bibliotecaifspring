package com.servicos.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.servicos.biblioteca.model.Livro;

@Component
public interface LivroService {
	
		
	public void salvar(Livro livro);
    public void excluir(Livro livro);
	public Livro carregar(Long id);
	public List<Livro>listar();
	

}
