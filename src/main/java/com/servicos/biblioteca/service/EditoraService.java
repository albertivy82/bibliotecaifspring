package com.servicos.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.servicos.biblioteca.model.Editora;

@Component
public interface EditoraService {
	
		
	public void salvar(Editora editora);
    public void excluir(Editora editora);
	public Editora carregar(Long id);
	public List<Editora>listar();
	

}
