package com.edugroupe.lightCmsForm.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(callSuper = true, exclude = {"images"})
@Entity
public class Galerie extends Contenu {
													private String titre;
	@OneToMany(mappedBy = "galerie") 				private Set<Image> images;
	
	public Set<Image> getImages() {
		if (images == null)
			images = new HashSet<>();
		return images;
	}
	
	public Galerie(int id, String nom, String titre) {
		super(id, nom);
		this.titre = titre;
	}
	
	
	
}
