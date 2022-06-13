package com.servicos.biblioteca.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Homecontroller{
	
	@RequestMapping("/login")
	public String login(@AuthenticationPrincipal User user, ModelMap model){
		model.addAttribute("usuario", user);
		if(user!=null) {
			model.addAttribute("usuario", user);
			return "redirect:/";
		}
	return "login";
	}
	
	@RequestMapping("/")
	public String index(@AuthenticationPrincipal User user, ModelMap model){
		model.addAttribute("usuario", user.getUsername());
		return "home";
	}
	
	@RequestMapping("/logout")
	public String logout(){
	return "login";
	}
	
	
	  
}
