package com.edugroupespringjpafnakform.metier;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString(exclude = {"auteur", "genres"})
public class Livre {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 	private int id;
																private String titre;
	@Column(length=20)											private String isbn;
																private int nbPages;
	@ManyToOne 													private Auteur auteur;
	@ManyToMany													private Set<Genre> genres;
	
	
	public Livre(int id, String titre, String isbn, int nbPages) {
		super();
		this.id = id;
		this.titre = titre;
		this.isbn = isbn;
		this.nbPages = nbPages;
	}

	
	
}
