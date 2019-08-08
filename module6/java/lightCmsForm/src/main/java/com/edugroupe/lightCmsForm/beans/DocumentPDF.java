package com.edugroupe.lightCmsForm.beans;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(callSuper = true)
@Entity
public class DocumentPDF extends Contenu
{
	private String titre;
	private String nomAuteur;
	
	public DocumentPDF(int id, String nom, String titre, String nomAuteur) {
		super(id, nom);
		this.titre = titre;
		this.nomAuteur = nomAuteur;
	}

}
