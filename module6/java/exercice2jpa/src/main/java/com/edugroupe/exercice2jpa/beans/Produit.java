package com.edugroupe.exercice2jpa.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Produit {
	private int id;
	private String nom;
	private double prix;
	private double poids;
	private int stock;
	
	// @transient permet d'indiquer a hibernate de ne pas tenir compte de ce getter ou attribut
	@Transient
	public double getPrixTTC() {
		return getPrix() * 1.20;
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() { return id; }
	
	public void setId(int id) { this.id = id; }
	
	@Column(name = "nom_prod", length = 150)
	public String getNom() { return nom; }
	
	public void setNom(String nom) { this.nom = nom; }
	public double getPrix() { return prix; }
	public void setPrix(double prix) { this.prix = prix; }
	public double getPoids() { return poids; }
	public void setPoids(double poids) { this.poids = poids; }
	public int getStock() { return stock; }
	public void setStock(int stock) { this.stock = stock; }
	
	public Produit() {}
	public Produit(int id, String nom, double prix, double poids, int stock) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.poids = poids;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", prix=" + prix + ", poids=" + poids + ", stock=" + stock
				+ ", getPrixTTC()=" + getPrixTTC() + "]";
	}




}
