package com.filmer.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filmer.entities.Actor;
import com.filmer.entities.Pelicula;
import com.filmer.service.IActorService;
import com.filmer.service.IPeliculasService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IPeliculasService peliculasService;
	
	@Autowired
	private IActorService actorService;
	
	@GetMapping("/peli-form")
	public String peliForm(Model model) {
		
		model.addAttribute("pelicula", new Pelicula());
		return "admin/peliForm";
	}
	
	@PostMapping("/save-peli")
	public String savePeli(@RequestParam(name="file", required = false)MultipartFile portada, Pelicula pelicula, 
			RedirectAttributes redirect) {
		
		if(!portada.isEmpty()) {
			String ruta = "C://Temp//uploads";
			String nombreUnico = UUID.randomUUID()+" "+ portada.getOriginalFilename();
			
			
			try {
				byte[] bytes = portada.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
				Files.write(rutaAbsoluta, bytes);
				pelicula.setPortada(nombreUnico);
				
				peliculasService.save(pelicula);
				redirect.addFlashAttribute("peliGuardada", "Película guardada");
				redirect.addFlashAttribute("peliParaActor", pelicula);
				
			}catch (Exception e) {
				e.getCause().getMessage();
				
			}
			
		}
		
		return "redirect:/actors/actors-form";
	}
	
	@GetMapping("/gestion-peliculas")
	public String listadoPeliculas(Model model) {
		model.addAttribute("peliculas", peliculasService.listadoPeliculas());
		
		return "admin/gestionPeliculas";
	}
	
	@GetMapping("/eliminar-pelicula/{id}")
	public String eliminarPelicula(@PathVariable Long id, RedirectAttributes redirect) {
		peliculasService.eliminarPelicula(id);
		redirect.addFlashAttribute("peliEliminada", "Pelicula eliminada");
		return "redirect:/admin/gestion-peliculas";
	}
	
	@GetMapping("/editar-form/{id}")
	public String editarFormulario(@PathVariable Long id, Model model) {
		
		Pelicula pelicula = null;
		if(id > 0) {
			pelicula = peliculasService.peliculaPorId(id);
			model.addAttribute("pelicula", pelicula);
		}
	
		return "admin/editarPelicula";
	}
	
	@PostMapping("/editar-pelicula")
	public String editarPelicula(@RequestParam(name="file")MultipartFile portada, Pelicula peli, RedirectAttributes redirect,
			@ModelAttribute("pelicula")Pelicula pelicula, Model model) {
		
		if(!portada.isEmpty()) {
			
			String ruta = "C://Temp//uploads";
			String nombreUnico = UUID.randomUUID() + " "  + portada.getOriginalFilename();
			// genera un numero aleatorio unico concatenando con el nombre del archivo
			
			try {
				
				byte[] bytes = portada.getBytes();
				Path rutaAbsoluta = Paths.get(ruta + "//" + nombreUnico);
				Files.write(rutaAbsoluta, bytes);
				pelicula.setPortada(nombreUnico);
				
				peliculasService.save(pelicula);
				
				redirect.addFlashAttribute("peliEditada", "Pelicula editada");

				
				
			}catch(Exception e) {
				e.getCause().getMessage();
			}
			
		}
		
		return "redirect:/admin/gestion-peliculas";
	}
	
	@GetMapping("/editar-actores{id}")
	public String editarActores(@PathVariable Long id, Model model) {
		
		Pelicula peliculaById = peliculasService.peliculaPorId(id);
		model.addAttribute("peliEncontrada", peliculaById);
		
		return "admin/edicionActores";
	}
	
	@GetMapping("/cargar-actor/{id}")
	public String cargarActor(@PathVariable Long id, Model model) {
		Actor actor = actorService.obtenerActor(id);
		model.addAttribute("actor", actor);
		
		return "admin/editarActorForm";
	}
	
	@PostMapping("/editar-actor")
	public String editarActor(@ModelAttribute("actor")Actor actor, RedirectAttributes redirect) {
		
		actorService.saveActor(actor);
		redirect.addFlashAttribute("actorEditado", "Actor modificado");
		
		return "redirect:/admin/gestion-peliculas";
	}
	
	@GetMapping("/eliminar-actor/{id}")
	public String eliminarActor(@PathVariable Long id, RedirectAttributes redirect) {
		
		actorService.eliminarActor(id);
		redirect.addFlashAttribute("actorEliminado", "Actor eliminado");
		
		return "redirect:/admin/gestion-peliculas";
	}
	
	@PostMapping("/save-actor")															
	public String saveActors(Actor actor, RedirectAttributes redirect, Model model) {
		
		actorService.saveActor(actor);
		redirect.addFlashAttribute("actorGuardado", "Actor guardado con exito");
		
		return "redirect:/admin/gestion-peliculas";
	}
	
}
