package com.edugroupe.springmoviesrestform.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.edugroupe.springmoviesrestform.metier.Acteur;
import com.edugroupe.springmoviesrestform.metier.Film;
import com.edugroupe.springmoviesrestform.metier.Realisateur;

@Configuration
public class SpringDataRestCustomization extends RepositoryRestConfigurerAdapter {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		super.configureRepositoryRestConfiguration(config);
		config.exposeIdsFor(Film.class, Realisateur.class, Acteur.class);
		
	}

	
}
