package com.servicos.biblioteca.controller;




import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String showForm(Editora editora, @AuthenticationPrincipal User user, ModelMap model) {
		model.addAttribute("usuario", user.getUsername());
		return "editora/editoraForm";
	}
	
	
	@PostMapping({"/editora","\\+{d}"})
	public String novaEditora(@Valid Editora editora,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "editora/editoraForm";
		}
		editoraService.salvar(editora);
	return "redirect:";
	}
	
	//LISTAR
	
	@GetMapping("/editoras")
	public String listagemEditores(@ModelAttribute("listagemEditoras") ModelMap model, @AuthenticationPrincipal User user, Model modell) {
		List<Editora> recebe = editoraService.listar();
		modell.addAttribute("usuario", user.getUsername());
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
