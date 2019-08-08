package com.edugroupe.lightCmsForm.beans;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(exclude = {"tags"})
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public class Contenu {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String nom;
																private LocalDate dateCreation;
																private LocalDate dateEdition;
	@ManyToMany 												private Set<Tag> tags;
	
	public Set<Tag> getTags() {
		if (tags ==null)
			tags = new HashSet<>();
		return tags;
	}

	public Contenu(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	
	
	@PrePersist
	public void beforeCreation() {
		this.dateCreation = LocalDate.now();
		this.dateEdition = LocalDate.now();
	}
	
	@PreUpdate
	public void beforeEdition() {
		this.dateEdition = LocalDate.now();
	}
	
	public void addTag(Tag t) {
		if (!getTags().contains(t)) {
			getTags().add(t); // c'est ce qui est important, ce qui inserera une ligne dans la table de jointure
			t.getContenus().add(this); // cosmétique
		}
	}
	
	

}
