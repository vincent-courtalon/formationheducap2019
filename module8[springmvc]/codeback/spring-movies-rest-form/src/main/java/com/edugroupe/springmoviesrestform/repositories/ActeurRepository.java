package com.edugroupe.springmoviesrestform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springmoviesrestform.metier.Acteur;

public interface ActeurRepository 
			extends PagingAndSortingRepository<Acteur, Integer> {

}
