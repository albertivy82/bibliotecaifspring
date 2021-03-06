package com.servicos.biblioteca.controller;




import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.servicos.biblioteca.model.Autor;
import com.servicos.biblioteca.model.Editora;
import com.servicos.biblioteca.model.Livro;
import com.servicos.biblioteca.service.AutorService;
import com.servicos.biblioteca.service.EditoraService;
import com.servicos.biblioteca.service.LivroService;


@Controller
@RequestMapping("/biblioteca")
public class LivroController{
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private AutorService autorService;
	
	@Autowired
	private EditoraService editoraService;
	
	//FORMULÁRIO VAZIO
	
	@GetMapping(value = "/livro")
	public ModelAndView showForm(Livro livro, @AuthenticationPrincipal User user) {
		ModelAndView mv = new ModelAndView("livro/livroForm");
		List<Autor> autor = autorService.listar();
		List<Editora> editora = editoraService.listar();
		mv.addObject("usuario", user.getUsername());
		mv.addObject("autor", autor);
		mv.addObject("editora", editora );
		
	return mv;
	}
	
	//PREENCHER
	@PostMapping({"/livro","\\+{d}"})
	public ModelAndView novoLivro(@Validated Livro livro, BindingResult bindingResult, Model model,
			RedirectAttributes attributes, HttpServletRequest httpServletRequest, @AuthenticationPrincipal User user) {
		
		if(bindingResult.hasErrors()) {
			return showForm(livro, user);
		}
		livroService.salvar(livro);
	return new ModelAndView("redirect:");
	}
	
	//LISTAR
	
	@GetMapping("/livros")
	public String listagemLivros(@ModelAttribute("listagemLivros") ModelMap model, @AuthenticationPrincipal User user, Model modell) {
		modell.addAttribute("usuario", user.getUsername());
		List<Livro> recebe = livroService.listar();
		model.addAttribute("livros", recebe);
	return "livro/livroLista";
	}
	 
	@PostMapping("/apagarlivro/{id}")
	 public String excluirlivroPorId(@PathVariable Long id) {
		
		 	Livro livro = livroService.carregar(id);
	        livroService.excluir(livro);
	        
	 return "redirect:/biblioteca/livros";
	 }
	
	@PostMapping("/editarlivro/{id}")
	 public ModelAndView editarLivroPorId(@PathVariable Long id, ModelMap model, @AuthenticationPrincipal User user) {
		Livro livro = livroService.carregar(id);
		ModelAndView livroEditar = showForm(livro, user);
		model.addAttribute(livro);
		return livroEditar;
	 }
	
	  
}
