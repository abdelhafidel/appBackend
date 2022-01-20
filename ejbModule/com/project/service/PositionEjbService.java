package com.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.dao.IpositionLocal;
import com.project.dao.IpositionRemote;
import com.project.modal.Position;

@Stateless(name = "POSITION")
public class PositionEjbService implements IpositionLocal, IpositionRemote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Position t) {
		em.persist(em.contains(t) ? t : em.merge(t));
	}

	@Override
	public void delteById(Position t) {
		em.remove(em.contains(t) ? t : em.merge(t));

	}

	@Override
	public Position update(Position t) {
		em.persist(em.contains(t) ? t : em.merge(t));
		return t;
	}

	@Override
	public Position findById(Position t) {

		return em.find(Position.class, t.getId());
	}

	@Override
	public List<Position> findAll() {
		Query q = em.createQuery("from Position");
		return q.getResultList();
	}

}
