package com.edugroupe.springuploadrepbaseform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springuploadrepbaseform.metier.Role;


public interface RoleRespository extends PagingAndSortingRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
