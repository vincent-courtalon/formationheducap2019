package com.edugroupe.springuploadrepbaseform.config;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edugroupe.springuploadrepbaseform.metier.Role;
import com.edugroupe.springuploadrepbaseform.metier.User;
import com.edugroupe.springuploadrepbaseform.repositories.RoleRespository;
import com.edugroupe.springuploadrepbaseform.repositories.UserRepository;



/*
 *  cette classe ecoute le ContextRefreshedEvent
 *  dans notre cas, cela sera déclenché après initalisation de tous les bean spring
 *  mais avant que le travail de l'application ne démarre réellement
 *  
 *  donc:
 *  	- tous les bean spring sont disponnible et pret à l'emploie
 *   		y compris les repositories
 *   	- mais aucun traitement de l'application normal (requette http, controller)
 *   		n'ont envore été éxécuté
 *  spring reconnaitra le fait qu'on implement l'interface ApplicationListener
 *  par contre, il faut bien déclarer notre classe comme service ou component
 *   si on veut qu'elle soit initialisée dans notre application
 */
@Service
public class DatabaseContentInitialiser implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRespository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (userRepository.count() == 0) {
			// il n'existe pas d'utilisateur dans la base
			// a nous de jouer!
			System.out.println("la base est vide, creation des user et role par defaut");
			Role r_admin = roleRepository.save(new Role(0, "ROLE_ADMIN"));
			Role r_user = roleRepository.save(new Role(0, "ROLE_USER"));
			
			User admin = new User(0, "admin", passwordEncoder.encode("admin"), true);
			admin.setRoles(new HashSet<>());
			admin.getRoles().add(r_admin);
			admin.getRoles().add(r_user);
			userRepository.save(admin);
			
			User vincent = new User(0, "vincent", passwordEncoder.encode("1234"), true);
			vincent.setRoles(new HashSet<>());
			vincent.getRoles().add(r_user);
			userRepository.save(vincent);
		}
		else
			System.out.println("la base contient déjà des utilisateurs");
		
	}

	
}
