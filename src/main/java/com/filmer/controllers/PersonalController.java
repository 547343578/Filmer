package com.filmer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.filmer.entities.Usuario;
import com.filmer.service.UsuarioService;

@Controller
@RequestMapping("/personal")
public class PersonalController {
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("/{username}")
	public String muestraComentarios(@PathVariable String username, Model model) {
		Usuario usuario = usuarioService.getByUsername(username).get();
		model.addAttribute("usuario", usuario);
		return "personalComentariosForm";
	}
}
