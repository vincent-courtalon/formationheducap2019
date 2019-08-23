package com.edugroupe.springmoviesrestform.metier;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude = {"films"})
public class Acteur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
	@Column(length = 100)									private String nom;
	@Column(length = 100)									private String prenom;
	@Column(length = 100)									private String email;
	@JsonIgnore
	@ManyToMany(mappedBy = "acteurs") 						private Set<Film> films;
	
	
	public Acteur(int id, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	

}
