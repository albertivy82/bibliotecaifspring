package com.servicos.biblioteca.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.servicos.biblioteca.model.Editora;
import com.servicos.biblioteca.service.EditoraService;


@Service("EditoraImplement")
public class EditoraImplement implements EditoraService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	public void salvar(Editora editora) {
		System.out.println("id, nates do if " + editora.getId());
		if(editora.getId()==null) {
			manager.persist(editora);
		}else {
			System.out.println(editora.getId());
			manager.merge(editora);
		}
	}
    
	 
	@Transactional
	public void excluir(Editora editora) {
    	
		if(manager.contains(editora)) {
    		manager.remove(editora);
    	}else{
    		manager.remove(manager.merge(editora));
    	}
	}
	
	@Transactional
	public Editora carregar(Long id) {
		Editora result = (Editora) manager.find(Editora.class, id);
		return result;
	}

	@Transactional
	public List<Editora> listar() {
		return manager.createQuery("from editora", Editora.class).getResultList();
	}

	
}
