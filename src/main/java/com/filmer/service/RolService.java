package com.filmer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer.dao.IRolDAO;
import com.filmer.entities.Rol;
import com.filmer.enums.RolNombre;

@Service
public class RolService {

	@Autowired
	private IRolDAO rolDao;
	
	public void guardaRol(Rol rol) {
		rolDao.save(rol);
	}
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolDao.findByRolNombre(rolNombre);
	}
	
	public boolean existsByRolNombre(RolNombre rolNombre) {
		return rolDao.existsByRolNombre(rolNombre);
	}
}
