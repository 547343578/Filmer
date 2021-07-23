package com.filmer.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filmer.entities.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{
	
	//puede encontrar o no por eso se utiliza Optional
	Optional<Usuario> findByUsername(String username);
	boolean existsByUsername(String username);
}
