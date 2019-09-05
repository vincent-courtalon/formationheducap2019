package com.edugroupe.springsecurityjpaform.security;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edugroupe.springsecurityjpaform.metier.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TypedQuery<User> q = em.createQuery(
				"SELECT u FROM User as u LEFT JOIN FETCH u.roles "
				+ " WHERE u.username = :username", 
				User.class);
		q.setParameter("username", username);
		try {
			User user = q.getSingleResult();
			return new MyUserDetails(user); // on retourne l'eutilisateur a l'interieur d'un userdetails
		} catch (NoResultException ex) {
			// pas d'utilisateur correspondant trouvé
			throw new UsernameNotFoundException("utilisateur inconnu");
		}
	}

}
