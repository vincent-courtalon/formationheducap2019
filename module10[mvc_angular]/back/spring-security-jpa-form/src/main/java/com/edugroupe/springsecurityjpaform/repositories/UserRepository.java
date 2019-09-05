package com.edugroupe.springsecurityjpaform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springsecurityjpaform.metier.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	User findByUsername(String username);
	
}
