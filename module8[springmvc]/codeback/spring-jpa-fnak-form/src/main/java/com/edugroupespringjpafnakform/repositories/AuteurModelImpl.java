package com.edugroupespringjpafnakform.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugroupespringjpafnakform.metier.Auteur;

@Service
public class AuteurModelImpl implements AuteurModel {
	
	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	@Transactional(readOnly = true)
	public List<Auteur> findAll() {
		return em.createQuery("from Auteur", Auteur.class).getResultList();
	}

}
