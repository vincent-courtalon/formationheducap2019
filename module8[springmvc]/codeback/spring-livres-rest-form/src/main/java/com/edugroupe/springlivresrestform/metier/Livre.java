package com.edugroupe.springlivresrestform.metier;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(exclude = {"auteur"})
@Entity
public class Livre {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
															private String titre;
															private int nbPages;
	@Column(length = 20)									private String isbn;
															private LocalDate dateSortie;
	@ManyToOne 												private Auteur auteur;
	
	public Livre(int id, String titre, int nbPages, String isbn, LocalDate dateSortie) {
		super();
		this.id = id;
		this.titre = titre;
		this.nbPages = nbPages;
		this.isbn = isbn;
		this.dateSortie = dateSortie;
	}
	
}
