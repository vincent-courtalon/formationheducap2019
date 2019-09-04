package com.edugroupe.springboutiqueimageform.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class Produit {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String nom;
																private double prix;
																private double poids;
	@ManyToOne 													private Categorie categorie;
	// FetchType.EAGER -> force le prechargement de la collection associée
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "produit")	private Set<Image> images;
	
	public Produit(int id, String nom, double prix, double poids) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
	}
	
	

}
