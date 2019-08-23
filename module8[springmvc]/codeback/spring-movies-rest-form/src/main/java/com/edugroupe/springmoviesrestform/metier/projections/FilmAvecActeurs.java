package com.edugroupe.springmoviesrestform.metier.projections;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.rest.core.config.Projection;

import com.edugroupe.springmoviesrestform.metier.Acteur;
import com.edugroupe.springmoviesrestform.metier.Film;

@Projection(types = { Film.class})
public interface FilmAvecActeurs {
	int getId();
	String getNom();
	int getDuree();
	LocalDate getDateSortie();
	Set<Acteur> getActeurs();
}
