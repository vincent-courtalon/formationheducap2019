package com.edugroupe.springvoiturerestxmlform.metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "voiture")
public class Voiture {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)	private int id;
															private String modele;
															private double prix;
															private int annee;
															private int puissance;
	
	@XmlAttribute(name = "voiture_id")
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	@XmlElement(name="modele")
	public String getModele() { return modele; }
	public void setModele(String modele) { this.modele = modele; }
	@XmlElement(name="prix")
	public double getPrix() { return prix; }
	public void setPrix(double prix) { this.prix = prix; }
	@XmlElement(name="annee")
	public int getAnnee() { return annee; }
	public void setAnnee(int annee) { this.annee = annee; }
	@XmlElement(name="puissance")
	public int getPuissance() { return puissance; }
	public void setPuissance(int puissance) { this.puissance = puissance; }
	
	public Voiture() {}
	public Voiture(int id, String modele, double prix, int annee, int puissance) {
		super();
		this.id = id;
		this.modele = modele;
		this.prix = prix;
		this.annee = annee;
		this.puissance = puissance;
	}
	
	

}
