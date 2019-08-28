package com.edugroupe.springlivresrestform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.edugroupe.springlivresrestform.metier.Auteur;

@RepositoryRestResource
public interface AuteurRepository extends PagingAndSortingRepository<Auteur, Integer> {

}
