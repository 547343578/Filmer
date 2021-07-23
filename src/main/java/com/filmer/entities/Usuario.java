package com.filmer.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String username;
	private String password;
	
	// asociar usuarios con el Rol
	@ManyToMany(fetch=FetchType.EAGER) // para que sean admin y usuario a la vez
	@JoinTable(name="usuario_rol", joinColumns=@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="rol_id"))
	private Set<Rol> roles = new HashSet<Rol>();  // Set hace que asociar un clave a un valor


	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="usuario")
	private List<Comentario> comentarios;
	
	public Usuario(Long id, String username, String password, Set<Rol> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	
	public Usuario() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Set<Rol> getRoles() {
		return roles;
	}


	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	

	public List<Comentario> getComentarios() {
		return comentarios;
	}
	
	
}
