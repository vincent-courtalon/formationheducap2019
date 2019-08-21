package com.edugroupe.springjpaform.repositories;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.edugroupe.springjpaform.metier.Livre;

public interface LivreModel {

	List<Livre> findAll();
	Livre findById(int id);
	Livre save(Livre l);
	boolean delete(int id);

}