package com.edugroupe.lightCmsForm.beans;

import java.util.HashSet;
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


@Getter @Setter @NoArgsConstructor @ToString(exclude = {"contenus"})
@Entity
public class Tag {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String libelle;
	@ManyToMany (mappedBy = "tags")								private Set<Contenu> contenus;
	
	public Set<Contenu> getContenus() {
		if (contenus == null)
			contenus = new HashSet<>();
		return contenus;
	}
	
	public Tag(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public void addContenu(Contenu c) {
		c.addTag(this);
	}
	
}
