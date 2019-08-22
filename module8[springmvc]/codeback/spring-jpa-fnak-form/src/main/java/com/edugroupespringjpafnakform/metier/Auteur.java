package com.edugroupespringjpafnakform.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude = {"livres"})
public class Auteur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 	private int id;
																private String nom;
																private String prenom;
	@OneToMany(mappedBy = "auteur") 							private Set<Livre> livres;
	
	public Auteur(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
}
