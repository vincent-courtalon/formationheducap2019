package com.edugroupe.springmoviesrestform.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.edugroupe.springmoviesrestform.metier.Film;

@RepositoryRestResource
public interface FilmRepository 
		extends PagingAndSortingRepository<Film, Integer> {

	List<Film> findByDureeGreaterThan(int duree);
}
