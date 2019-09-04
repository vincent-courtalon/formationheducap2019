package com.edugroupe.springboutiqueimageform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springboutiqueimageform.metier.Produit;

public interface ProduitRepository extends PagingAndSortingRepository<Produit, Integer> {

}
