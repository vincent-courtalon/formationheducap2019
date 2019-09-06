package com.edugroupe.springuploadrepbaseform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springuploadrepbaseform.metier.User;


public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	User findByUsername(String username);
	
}
