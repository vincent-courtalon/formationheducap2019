package com.edugroupe.exerciceFilmForm.beans;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Producteur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	 	private int id;
																private String nom;
																private LocalDate dateNaissance;
	@OneToMany(mappedBy = "producteur")							private Set<Film> films;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public LocalDate getDateNaissance() { return dateNaissance; }
	public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
	public Set<Film> getFilms() {
		if (films == null)
			films = new HashSet<>();
		return films; 
	}
	public void setFilms(Set<Film> films) { this.films = films; }
	
	public Producteur() {}
	public Producteur(int id, String nom, LocalDate dateNaissance) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateNaissance = dateNaissance;
	}
	
	@Override
	public String toString() {
		return "Producteur [id=" + id + ", nom=" + nom + ", dateNaissance=" + dateNaissance + "]";
	}
	
	
	
}
