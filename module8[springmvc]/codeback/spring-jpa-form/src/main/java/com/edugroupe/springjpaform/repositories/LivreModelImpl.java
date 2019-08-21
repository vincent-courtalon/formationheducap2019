package com.edugroupe.springjpaform.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugroupe.springjpaform.metier.Livre;

@Service
public class LivreModelImpl implements LivreModel {
	
	// cette anotation spéciale indique à spring/jpa ou injecter l'entity manager
	// en provenance de l'entity manager factory
	@PersistenceContext
	private EntityManager em;
	
	// l'annotation transactional indiquera au transaction manager
	// de préparer l'entity manager et une transaction au démaarage de cette fonction
	// et de commiter et fermer l'entity manager à la sortie de la fonction
	// tout cela automatiquement
	// (en interne, cela passe par de l'aop et une classe proxy, il est très recommandé de passer
	// par une interface pour injecter nore LivreModel dans les controllers)
	@Override
	@Transactional(readOnly = true)
	public List<Livre> findAll() {
		return em.createQuery("FROM Livre", Livre.class).getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Livre findById(int id) {
		return em.find(Livre.class, id);
	}
	
	@Override
	@Transactional
	public Livre save(Livre l) {
		if (l.getId() == 0) {
			em.persist(l);
		}
		else {
			l = em.merge(l);
		}
		return l;
	}
	
	@Override
	@Transactional
	public boolean delete(int id) {
		Livre l = em.find(Livre.class, id);
		if (l == null) return false;
		em.remove(l);
		return true;
	}
	
}
