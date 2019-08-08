package com.edugroupe.jpaheritageSingleTableForm.beans;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @ToString(callSuper = true)
@Entity 
@DiscriminatorValue(value = "2")
public class Client extends Personne {
	/*@Column(nullable = false)*/	private String email;
									private double soldeCompte;
	
	
	public Client(int id, String nom, String prenom, String email, double soldeCompte) {
		super(id, nom, prenom);
		this.email = email;
		this.soldeCompte = soldeCompte;
	}
	
}
