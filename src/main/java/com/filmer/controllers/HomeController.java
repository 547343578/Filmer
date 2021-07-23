package com.filmer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.filmer.entities.Pelicula;
import com.filmer.service.IPeliculasService;
import com.filmer.service.UsuarioService;

@Controller
public class HomeController {

	@Autowired
	private IPeliculasService peliculasService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("")
	public String home(Model model, Authentication auth) {
		
		if(auth != null) {
			String username = auth.getName();
			Long id = usuarioService.getByUsername(username).get().getId();
			model.addAttribute("username", username);
			model.addAttribute("id", id);
		}
		
		model.addAttribute("peliculas", peliculasService.listadoPeliculas());
		model.addAttribute("pelicula", new Pelicula());		
		return "home";
	}
	
	@GetMapping("/buscar")		// Parametro de la url      // recibe "pelicula" y almacena en new pelicula
	public String buscarPelicula(@RequestParam String titulo, @ModelAttribute("pelicula")Pelicula pelicula, Model model) {
		
		Pelicula peli = peliculasService.peliPorTitulo(titulo);
		
		if(titulo != null) {
			model.addAttribute("peli", peli);
		}
		
		if(peli == null) {
			model.addAttribute("peliNoEncontrada", "Sin resultados...");
		}
		
		return "peliBuscador";
	}
	
	
}
