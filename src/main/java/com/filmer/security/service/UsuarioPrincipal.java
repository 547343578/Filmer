package com.filmer.security.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.filmer.entities.Usuario;

public class UsuarioPrincipal implements UserDetails{

	private final Long id;
	private final String username;
	private final String password;
	
	private final Collection<? extends GrantedAuthority> authorities;
	
	
	
	public UsuarioPrincipal(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

// Convertir nuestro usuario al usuario que maneja internamente el spring security
	public static UsuarioPrincipal build(Usuario usuario) {
		
		// meter las autoridades que tiene el usuario
		List<GrantedAuthority> authorities = 
				usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().toString()))
				.collect(Collectors.toList());
		
		
		return new UsuarioPrincipal(usuario.getId(), usuario.getUsername(), usuario.getPassword(), authorities);
	
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		return authorities;
	}
	@Override
	public String getPassword() {
		
		return password;
	}
	@Override
	public String getUsername() {
		
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}


	public Long getId() {
		return id;
	}

	
}
