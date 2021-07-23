package com.filmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer.dao.IComentarioDAO;
import com.filmer.entities.Comentario;
import com.filmer.service.IComentarioService;

@Service
public class ComentarioServiceImpl implements IComentarioService{

	@Autowired
	private IComentarioDAO comentarioDao;
	
	@Override
	public void saveComentario(Comentario comentario) {
		comentarioDao.save(comentario);
	}

	@Override
	public Comentario buscarPorId(Long id) {
		return comentarioDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarComentario(Long id) {
		comentarioDao.deleteById(id);
	}

}
