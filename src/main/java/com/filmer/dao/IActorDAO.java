package com.filmer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.filmer.entities.Actor;

@Repository
public interface IActorDAO extends JpaRepository<Actor, Long>{

}
