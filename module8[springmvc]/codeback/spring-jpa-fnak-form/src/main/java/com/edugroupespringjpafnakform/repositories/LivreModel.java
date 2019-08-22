package com.edugroupespringjpafnakform.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.edugroupespringjpafnakform.metier.Genre;
import com.edugroupespringjpafnakform.metier.Livre;

public interface LivreModel {

	List<Livre> findAll(boolean withGenres);

	Livre findById(int id);

	Livre save(Livre livre, int auteurId);

	boolean delete(int id);

	List<Genre> findLivreGenre(int livreId);

	List<Genre> findLivreNotGenre(int livreId);

	boolean addGenreToLivre(int livreId, int genreId);

	boolean removeGenreToLivre(int livreId, int genreId);

}