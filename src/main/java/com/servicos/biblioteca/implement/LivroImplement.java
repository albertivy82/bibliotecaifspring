package com.servicos.biblioteca.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.servicos.biblioteca.model.Livro;
import com.servicos.biblioteca.service.LivroService;

@Service("LivroImplement")
public class LivroImplement implements LivroService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Livro livro) {
		if(livro.getId()==null) {
			manager.persist(livro);
		}else {
			System.out.println(livro.getId());
			manager.merge(livro);
		}
	}
    
	 
	@Transactional
	public void excluir(Livro livro) {
    	
		if(manager.contains(livro)) {
    		manager.remove(livro);
    	}else{
    		manager.remove(manager.merge(livro));
    	}
	}
	
	@Transactional
	public Livro carregar(Long id) {
		Livro result = (Livro) manager.find(Livro.class, id);
		return result;
	}

	@Transactional
	public List<Livro> listar() {
		return manager.createQuery("from livro", Livro.class).getResultList();
	}
	
}
