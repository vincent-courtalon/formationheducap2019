package com.edugroupe.lightCmsForm.beans;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(callSuper = true, exclude = {"galerie"})
@Entity
public class Image extends Contenu{
														private String fileName;
														private String typeImage;
	@ManyToOne 											private Galerie galerie;
	
	
	public Image(int id, String nom, String fileName, String typeImage) {
		super(id, nom);
		this.fileName = fileName;
		this.typeImage = typeImage;
	}

}
