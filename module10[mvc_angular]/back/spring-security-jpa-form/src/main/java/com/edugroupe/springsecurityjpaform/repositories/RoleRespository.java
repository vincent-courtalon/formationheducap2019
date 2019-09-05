package com.edugroupe.springsecurityjpaform.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroupe.springsecurityjpaform.metier.Role;

public interface RoleRespository extends PagingAndSortingRepository<Role, Integer> {

	Role findByRoleName(String roleName);
}
