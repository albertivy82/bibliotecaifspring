package com.servicos.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.servicos.biblioteca.model.Autor;

@Component
public interface AutorService {
	
		
	public void salvar(Autor autor);
    public void excluir(Autor autor);
	public Autor carregar(Long id);
	public List<Autor>listar();
	

}
