package com.filmer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmer.dao.IActorDAO;
import com.filmer.entities.Actor;
import com.filmer.service.IActorService;

@Service
public class ActorServiceImpl  implements IActorService{

	@Autowired
	private IActorDAO actorDao;
	
	@Override
	public void saveActor(Actor actor) {
		actorDao.save(actor);
	}

	@Override
	public Actor obtenerActor(Long id) {
		return actorDao.findById(id).orElse(null);
	}

	@Override
	public void eliminarActor(Long id) {
		actorDao.deleteById(id);
	}

}
