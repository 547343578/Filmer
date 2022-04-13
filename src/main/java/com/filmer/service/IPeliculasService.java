package com.filmer.service;

import java.util.List;

import com.filmer.entities.Pelicula;

public interface IPeliculasService {
	void save(Pelicula pelicula);
	List<Pelicula> listadoPeliculas();
	Pelicula peliculaPorId(Long id);
	void eliminarPelicula(Long id);
	Pelicula peliPorTitulo(String titulo);
}
