package com.filmer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

import com.filmer.enums.RolNombre;

@Entity
//@Table(name="roles")
public class Rol {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NonNull
	@Enumerated(EnumType.STRING)
	@Column(unique=true)
	private RolNombre rolNombre;

	
	
	public Rol(int id, RolNombre rolNombre) {
		this.id = id;
		this.rolNombre = rolNombre;
	}
	
	public Rol() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	
	
	
}
