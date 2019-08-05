package com.edugroupe.associationsMappingForm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
															private String libelle;
	@OneToMany(cascade = {CascadeType.REMOVE, CascadeType.DETACH}, mappedBy = "categorie"/*, fetch = FetchType.EAGER*/)		private Set<Produit> produits;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getLibelle() { return libelle; }
	public void setLibelle(String libelle) { this.libelle = libelle; }
	public Set<Produit> getProduits() {
		if (produits == null)
			produits = new HashSet<>();
		return produits; 
	}
	public void setProduits(Set<Produit> produits) { this.produits = produits; }
	
	public Categorie() {}
	public Categorie(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Categorie [id=" + id + ", libelle=" + libelle + "]";
	}
	
	
}
