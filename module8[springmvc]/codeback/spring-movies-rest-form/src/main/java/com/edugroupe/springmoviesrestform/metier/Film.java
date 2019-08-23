package com.edugroupe.springmoviesrestform.metier;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude = {"acteurs", "realisateur"})
public class Film {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
	@Column(length = 100)								private String nom;
														private int duree;
														private LocalDate dateSortie;
	@JsonIgnore
	@ManyToMany											private Set<Acteur> acteurs;
	@ManyToOne											private Realisateur realisateur;
	
	public Film(int id, String nom, int duree, LocalDate dateSortie) {
		super();
		this.id = id;
		this.nom = nom;
		this.duree = duree;
		this.dateSortie = dateSortie;
	}
	
	

}
