package com.edugroupe.springboutiqueimageform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springboutiqueimageform.metier.Image;

public interface ImageRepository 
		extends PagingAndSortingRepository<Image, Integer>,
				ImageRepositoryCustom {

}
