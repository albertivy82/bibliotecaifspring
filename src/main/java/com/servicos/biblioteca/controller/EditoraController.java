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
import com.servicos.biblioteca.model.Editora;
import com.servicos.biblioteca.service.EditoraService;




@Controller
@RequestMapping("/biblioteca")
public class EditoraController{
	
	@Autowired
	private EditoraService editoraService;
	
	//FORMULÁRIO VAZIO
	
	@GetMapping(value = "/editora")
	public String showForm(Editora editora) {
	return "editora/editoraForm";
	}
	
	
	@PostMapping({"/editora","\\+{d}"})
	public String novaEditora(@Valid Editora editora,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			System.out.println("erro: " + bindingResult.toString());
			return "editora/editoraForm";
		}
		editoraService.salvar(editora);
	return "redirect:";
	}
	
	//LISTAR
	
	@GetMapping("/editoras")
	public String listagemEditores(@ModelAttribute("listagemEditoras") ModelMap model) {
		List<Editora> recebe = editoraService.listar();
		model.addAttribute("editoras", recebe);
	return "editora/editoraLista";
	}
	 
	@PostMapping("/apagareditora/{id}")
	 public String excluirEditoraPorId(@PathVariable Long id) {
		
		Editora editora = editoraService.carregar(id);
	        editoraService.excluir(editora);
	        
	 return "redirect:/biblioteca/editoras";
	 }
	
	@PostMapping("/editareditoras/{id}")
	 public String editarPorId(@PathVariable Long id, ModelMap model) {
		Editora editora = editoraService.carregar(id);
		model.addAttribute(editora);
		return "editora/editoraForm";
	 }
	
	  
}
