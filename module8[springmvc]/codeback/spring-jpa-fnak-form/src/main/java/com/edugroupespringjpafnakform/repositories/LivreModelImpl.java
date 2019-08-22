package com.edugroupespringjpafnakform.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugroupespringjpafnakform.metier.Auteur;
import com.edugroupespringjpafnakform.metier.Genre;
import com.edugroupespringjpafnakform.metier.Livre;

@Service
public class LivreModelImpl implements LivreModel {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<Livre> findAll(boolean withGenres) {
		if (withGenres)
			return em.createQuery("SELECT DISTINCT(li) FROM Livre AS li LEFT JOIN FETCH li.genres", Livre.class).getResultList();
		else
			return em.createQuery("FROM Livre", Livre.class).getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Livre findById(int id) {
		return em.find(Livre.class, id);
	}
	
	@Override
	@Transactional
	public Livre save(Livre livre, int auteurId) {
		
		Auteur a = em.find(Auteur.class, auteurId);
		livre.setAuteur(a);
		
		if (livre.getId() == 0) {
			em.persist(livre);
		}
		else {
			Livre oldLivre = em.find(Livre.class, livre.getId());
			livre.setGenres(oldLivre.getGenres());
			livre = em.merge(livre);
		}
		return livre;
	}
	
	@Override
	@Transactional
	public boolean delete(int id) {
		Livre l = em.find(Livre.class, id);
		if (l == null) return false;
		
		em.remove(l);
		return true;
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Genre> findLivreGenre(int livreId) {
		TypedQuery<Genre> q = em.createQuery(
				"select g from Genre as g join g.livres as li "
				+ "	where li.id = :livreId", Genre.class);
		q.setParameter("livreId", livreId);
		return q.getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Genre> findLivreNotGenre(int livreId) {
		TypedQuery<Genre> q = em.createQuery(
				  " SELECT g FROM Genre AS g "
				+ "	WHERE NOT EXISTS "
				+ " ( "
				+ " 	SELECT li FROM Livre AS li join li.genres AS g2 "
				+ " 			  WHERE li.id = :livreId AND g2.id = g.id"
				+ " ) ", Genre.class);
		q.setParameter("livreId", livreId);
		return q.getResultList();
	}
	
	@Override
	@Transactional
	public boolean addGenreToLivre(int livreId, int genreId) {
		Livre livre = em.find(Livre.class, livreId);
		Genre genre = em.find(Genre.class, genreId);
		if (livre == null || genre == null)
			return false;
		livre.getGenres().add(genre);
		return true;
	}
	
	@Override
	@Transactional
	public boolean removeGenreToLivre(int livreId, int genreId) {
		Livre livre = em.find(Livre.class, livreId);
		Genre genre = em.find(Genre.class, genreId);
		if (livre == null || genre == null)
			return false;
		livre.getGenres().remove(genre);
		return true;
	}
	
	
}
