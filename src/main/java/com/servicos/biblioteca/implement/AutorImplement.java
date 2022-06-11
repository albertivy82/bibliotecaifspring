package com.servicos.biblioteca.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.servicos.biblioteca.model.Autor;
import com.servicos.biblioteca.service.AutorService;

@Service("AutorImplement")
public class AutorImplement implements AutorService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Autor autor) {
		System.out.println("id, nates do if " + autor.getId());
		if(autor.getId()==null) {
			manager.persist(autor);
		}else {
			System.out.println(autor.getId());
			manager.merge(autor);
		}
	}
    
	 
	@Transactional
	public void excluir(Autor autor) {
    	
		if(manager.contains(autor)) {
    		manager.remove(autor);
    	}else{
    		manager.remove(manager.merge(autor));
    	}
	}
	
	@Transactional
	public Autor carregar(Long id) {
		Autor result = (Autor) manager.find(Autor.class, id);
		return result;
	}

	@Transactional
	public List<Autor> listar() {
		return manager.createQuery("from autor", Autor.class).getResultList();
	}
	
}
