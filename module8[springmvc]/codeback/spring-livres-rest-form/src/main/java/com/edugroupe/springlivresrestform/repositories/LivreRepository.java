package com.edugroupe.springlivresrestform.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.springlivresrestform.metier.Livre;

@RepositoryRestResource
public interface LivreRepository extends PagingAndSortingRepository<Livre, Integer> {

	Page<Livre> findByTitreContaining(String searchTerm, Pageable page);
}
