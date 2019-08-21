package com.edugroupe.springVilleForm.metier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Ville {
	private int id;
	private String nom;
	private int population;
	private double surface;
	private String pays;

}
