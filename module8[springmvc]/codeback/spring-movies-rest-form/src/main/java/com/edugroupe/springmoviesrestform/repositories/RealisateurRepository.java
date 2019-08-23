package com.edugroupe.springmoviesrestform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.springmoviesrestform.metier.Realisateur;

@RepositoryRestResource
public interface RealisateurRepository 
		extends PagingAndSortingRepository<Realisateur, Integer> {

}
