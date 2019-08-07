package com.edugroupe.exerciceFilmForm.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Film {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String titre;
																private LocalDate dateSortie;
																private int duree;
	@ManyToOne 													private Producteur producteur;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getTitre() { return titre; }
	public void setTitre(String titre) { this.titre = titre; }
	public LocalDate getDateSortie() { return dateSortie; }
	public void setDateSortie(LocalDate dateSortie) { this.dateSortie = dateSortie; }
	public int getDuree() { return duree; }
	public void setDuree(int duree) { this.duree = duree; }
	public Producteur getProducteur() { return producteur; }
	public void setProducteur(Producteur producteur) { this.producteur = producteur; }
	
	public Film() {}
	public Film(int id, String titre, LocalDate dateSortie, int duree) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateSortie = dateSortie;
		this.duree = duree;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", dateSortie=" + dateSortie + ", duree=" + duree + "]";
	}

}
