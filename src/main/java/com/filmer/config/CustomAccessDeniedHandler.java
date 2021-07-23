package com.filmer.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	// hacer una redireccion en caso de que no estamos autorizados
	// cuando no tenemos permisos para acceder a una determinada ruta
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		// saco el path donde estoy y lo concadeno con el /forbidden, que es una ruta donde entrar los no autorizados
		response.sendRedirect(request.getContextPath() + "/forbidden");
		
	}

}
