package com.project.service;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.dao.IphoneLocal;
import com.project.dao.IphoneRemote;
import com.project.modal.Smartphone;

@Stateless(name = "SPHONE")
@PermitAll
public class SPhoneEjbService implements IphoneRemote, IphoneLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Smartphone t) {
		em.persist(t);
	}

	@Override
	public void delteById(Smartphone t) {
		Smartphone s2 = findById(t);
		em.remove(em.contains(s2) ? s2 : em.merge(s2));
	}

	@Override
	public Smartphone update(Smartphone t) {
		em.persist(em.contains(t) ? t : em.merge(t));
		return t;
	}

	@Override
	public Smartphone findById(Smartphone t) {

		return em.find(Smartphone.class, t.getId());
	}

	@Override
	public List<Smartphone> findAll() {
		Query q = em.createQuery("from Smartphone");
		return q.getResultList();
	}

	@Override
	public Smartphone findByEmei(String imei) {
		Query q = em.createQuery("select s from Smartphone s where imei like :x");
		q.setParameter("x", imei);


		try {
			Smartphone s =  (Smartphone) q.getResultList().get(0);
			return s;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new Smartphone();
	}

}
