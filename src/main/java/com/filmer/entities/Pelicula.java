package com.filmer.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="peliculas")
public class Pelicula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String portada;
	
	@Column(name="fecha_estreno")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso=ISO.DATE)
	private Date fechaEstreno;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="pelicula")
	private List<Actor> actores;

	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy="pelicula")
	private List<Comentario> comentarios;
	
	public Pelicula(Long id, String titulo, String portada, Date fechaEstreno, List<Actor> actores, List<Comentario> comentarios) {
		this.id = id;
		this.titulo = titulo;
		this.portada = portada;
		this.fechaEstreno = fechaEstreno;
		this.actores = actores;
		this.comentarios = comentarios;
	}
	
	public Pelicula() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public Date getFechaEstreno() {
		return fechaEstreno;
	}

	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}

	public List<Actor> getActores() {
		return actores;
	}

	public void setActores(List<Actor> actores) {
		this.actores = actores;
	}
	
	
	public void addActores(Actor actor) {
		actores.add(actor);
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	
}
