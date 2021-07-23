package com.filmer.service;

import com.filmer.entities.Comentario;

public interface IComentarioService {
	void saveComentario(Comentario comentario);
	Comentario buscarPorId(Long id);
	void eliminarComentario(Long id);
}
