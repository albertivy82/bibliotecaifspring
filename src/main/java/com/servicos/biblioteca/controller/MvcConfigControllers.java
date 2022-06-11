package com.servicos.biblioteca.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigControllers implements WebMvcConfigurer {
    
	   //este método adiciona os controladores de requisiçoes URL (rotas)
	   public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/home").setViewName("home");
	
		 
	}
}
