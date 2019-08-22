package com.edugroupe.springjpablogform.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugroupe.springjpablogform.metier.Post;

@Service
public class PostModelImpl implements PostModel {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Post> findAll() {
		return em.createQuery("from Post", Post.class).getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Post findByID(int id) {
		return em.find(Post.class, id);
	}
	
	@Override
	@Transactional
	public Post save(Post p) {
		if (p.getId() == 0) {
			em.persist(p);
		}
		else  {
			Post po = em.find(Post.class, p.getId());
			p.setDateCreation(po.getDateCreation());
			p = em.merge(p);
		}
		return p;
	}
	
	@Override
	@Transactional
	public boolean delete(int id) {
		Post p = em.find(Post.class,id);
		if (p == null)
			return false;
		em.remove(p);
		return true;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Post> searchByAuteur(String searchTerm) {
		TypedQuery<Post> q = em.createQuery("select p from Post as p where p.auteur like :searchterm", Post.class);
		q.setParameter("searchterm", "%" + searchTerm + "%");
		return q.getResultList();
	}
	
	
}
