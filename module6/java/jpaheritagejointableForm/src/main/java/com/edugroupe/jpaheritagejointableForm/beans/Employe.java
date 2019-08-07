package com.edugroupe.jpaheritagejointableForm.beans;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(callSuper = true)
@Entity
public class Employe  extends Personne{
	private String poste;
	private double salaire;
	
	public Employe(int id, String nom, String prenom, String poste, double salaire) {
		super(id, nom, prenom);
		this.poste = poste;
		this.salaire = salaire;
	}
	
}
