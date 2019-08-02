package com.edugroupe.premierjpaform.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// l'annotation 'Entity' devant la classe indique que c'est un EJB3 de type entité
// qu'hibernate devra gerer
@Entity
public class Message {
	private int id;
	private String titre;
	private String corps;

	// annotation 'Id' devant soit: un getter, soir l'attribut, indique à hibernate
	// quelle est la clef primaire de cet objet metier
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // generatedValue pour demander a la base de generer les valeur des clef primaire	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getCorps() {return corps;}
	public void setCorps(String corps) {this.corps = corps;}



	public Message() {this(0,"","");}

	public Message(int id, String titre, String corps) {
		this.id = id;
		this.titre = titre;
		this.corps = corps;
	}
	

}
