package com.filmer.service;

import com.filmer.entities.Actor;

public interface IActorService {

	void saveActor(Actor actor);
	Actor obtenerActor(Long id);
	void eliminarActor(Long id);
}
