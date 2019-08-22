package com.edugroupespringjpafnakform.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude = {"livres"})
public class Genre {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 	private int id;
																private String libelle;
	@ManyToMany(mappedBy = "genres")							private Set<Livre> livres;
	
	
	public Genre(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
}
