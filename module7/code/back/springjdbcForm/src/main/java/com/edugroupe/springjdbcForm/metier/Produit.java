package com.edugroupe.springjdbcForm.metier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Produit {
	private int id;
	private String nom;
	private double prix;
	private double poids;

}
