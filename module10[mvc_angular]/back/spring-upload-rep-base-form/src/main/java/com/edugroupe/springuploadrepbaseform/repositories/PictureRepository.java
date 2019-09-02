package com.edugroupe.springuploadrepbaseform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springuploadrepbaseform.metier.Picture;

public interface 	PictureRepository 
		extends 	PagingAndSortingRepository<Picture, Long>,
					PictureRepositoryCustom {
	
}
