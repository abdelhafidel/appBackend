package com.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.project.dao.IuserLocal;
import com.project.dao.IuserRemote;
import com.project.modal.Smartphone;
import com.project.modal.User;

@Stateless(name = "USER")
@PermitAll
public class  UserEjbService implements IuserRemote,IuserLocal{
	
	@PersistenceContext
	private EntityManager em;
	

	@Override
	public void create(User t) {
		em.persist(t);
	}

	@Override
	public void delteById(User t) {
		User t2 = findById(t);
		em.remove(em.contains(t2) ? t2 : em.merge(t2));
		
	}

	@Override
	public User update(User t) {
		em.persist(em.contains(t) ? t : em.merge(t));
		return t;
	}

	@Override
	public User findById(User t) {
		
		return (User) em.find(User.class, t.getId());
	}

	@Override
	public List<User> findAll() {
		Query q = em.createQuery("select u from User u");
		List<User> users =  q.getResultList();
		System.out.println(users);
		return users;
	}
	
	@Override
	public List<Smartphone> findAllSmartphoneByUser(String nom) {
		System.out.println(nom);
		Query q = em.createNativeQuery("select s.id,imei from smartphone s inner join user u on u.id = s.user_id where nom like :x");
		q.setParameter("x", nom);


		try {
			List<Smartphone> s =  (List<Smartphone>) q.getResultList();
			return s;
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ArrayList<Smartphone>();
	}
	

}
