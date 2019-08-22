package com.edugroupe.springjpablogform.metier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Post {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	@Column(length = 100)									private String titre;
	@Column(length = 500)									private String corps;
															private LocalDate dateCreation;
	@Column(length = 100)									private String auteur;
	
	// ce getter sera accessible depuis jsp comme un champ normal nommé "formatedDateCreation"
	// il sera ignoré par hibernate, car on a noté les attributs, et non les getter
	public String getFormatedDateCreation() {
		return dateCreation.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
	
	@PrePersist
	public void avantInsertion() {
		setDateCreation(LocalDate.now());
	}
}
