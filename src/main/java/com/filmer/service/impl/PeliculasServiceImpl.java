package com.filmer.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer.dao.IPeliculasDAO;
import com.filmer.entities.Pelicula;
import com.filmer.service.IPeliculasService;

@Service
public class PeliculasServiceImpl implements IPeliculasService{

	@Autowired
	private IPeliculasDAO peliculasDao;
	
	@Override
	public void save(Pelicula pelicula) {
		
		peliculasDao.save(pelicula);
		
	}

	@Override
	public List<Pelicula> listadoPeliculas() {
		
		return peliculasDao.findAll();
	}

	@Override
	public Pelicula peliculaPorId(Long id) {
		return peliculasDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarPelicula(Long id) {
		peliculasDao.deleteById(id);
		
	}

	@Override
	public Pelicula peliPorTitulo(String titulo) {
		return peliculasDao.findByTitulo(titulo);
	}
}
