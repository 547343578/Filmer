package com.filmer.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmer.entities.Comentario;
import com.filmer.entities.Pelicula;
import com.filmer.entities.Usuario;
import com.filmer.service.IComentarioService;
import com.filmer.service.IPeliculasService;
import com.filmer.service.UsuarioService;

@Controller
@RequestMapping("/peliculas")
@SessionAttributes("comentario")
public class PeliculaController {
	
	@Autowired
	private IPeliculasService peliculasService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private IComentarioService comentarioService;
	
	@GetMapping("/ver-comentarios/{id}")
	public String peli(@PathVariable Long id, Model model, Authentication auth) {
		
		if(auth != null) {
			String username = auth.getName();
			model.addAttribute("username", username);
		}
		
		Pelicula pelicula = peliculasService.peliculaPorId(id);
		model.addAttribute("pelicula", pelicula);
		
		return "comentarios/verComentarios";
	}
	
	@GetMapping("/cargar-peli-para-comentar/{id}")
	public String peliParaComentar(@PathVariable Long id, Model model) {
		Pelicula pelicula = peliculasService.peliculaPorId(id);
		Comentario comentario = new Comentario();
		comentario.setPelicula(pelicula);
		model.addAttribute("comentario", comentario);
		model.addAttribute("pelicula", pelicula);
		return "comentarios/comentarioForm";
	}
	
	@PostMapping("/save-comentario")
	public String guardarComentario(Comentario comentario, Authentication auth,
			HttpSession session, RedirectAttributes redirect) {
		String username = auth.getName();
		Optional<Usuario> usuario = usuarioService.getByUsername(username);
		//System.out.println(usuario.get().getUsername());
		comentario.setUsuario(usuario.get());
		comentarioService.saveComentario(comentario);
		redirect.addFlashAttribute("comentarioGuardado", "Comentario guardado");
		return "redirect:/";
	}
}
