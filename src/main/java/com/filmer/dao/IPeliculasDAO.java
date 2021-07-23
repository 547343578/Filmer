package com.filmer.dao;

import org.springframework.stereotype.Repository;
import com.filmer.entities.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IPeliculasDAO extends JpaRepository<Pelicula, Long>{
	Pelicula findByTitulo(String titulo);
}
