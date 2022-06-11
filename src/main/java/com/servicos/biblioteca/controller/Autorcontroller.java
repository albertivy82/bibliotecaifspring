package com.servicos.biblioteca.controller;




import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servicos.biblioteca.model.Autor;
import com.servicos.biblioteca.service.AutorService;


@Controller
@RequestMapping("/biblioteca")
public class Autorcontroller{
	
	@Autowired
	private AutorService autorService;
	
	//FORMULÁRIO VAZIO
	
	@GetMapping(value = "/autor")
	public String showForm(Autor autor) {
	return "autor/autorForm";
	}
	
	//PREENCHER
	@PostMapping({"/autor","\\+{d}"})
	public String novoAutor(@Valid Autor autor,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println("erro: " + bindingResult.toString());
			return "autor/autorForm";
		}
		autorService.salvar(autor);
	return "redirect:";
	}
	
	//LISTAR
	
	@GetMapping("/lista")
	public String listagemAutores(@ModelAttribute("listagem") ModelMap model) {
		List<Autor> recebe = autorService.listar();
		model.addAttribute("autores", recebe);
	return "autor/autorLista";
	}
	 
	@PostMapping("/apagar/{id}")
	 public String excluirPorId(@PathVariable Long id) {
		
		 	Autor autor = autorService.carregar(id);
	        autorService.excluir(autor);
	        
	 return "redirect:/biblioteca/lista";
	 }
	
	@PostMapping("/editar/{id}")
	 public String editarPorId(@PathVariable Long id, ModelMap model) {
		Autor autor = autorService.carregar(id);
		model.addAttribute(autor);
		return "autor/autorForm";
	 }
	
	  
}
