package com.edugroupe.basicmappingForm.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moutons")
public class Client {
	/*
	 * On peu annoter sur les attributs ou sur getter
	 * ATTENTION, on ne peut melanger, dans une même entite, les annotations attribut ou getter
	 * c'est l'annotation @Id qui determine si dans cette entite, on regardera les attribut
	 * ou les getter
	 * 
	 * ce n'est pas un choix completement anodin
	 * si vous annotez sur les attributs, hibernate ecrira/lira directement dedans
	 * sans passer par les getter/setter
	 * si vous annotez sur les getter/setter, hibernate appelera vos getter/setter pour
	 * lire /ecrire dans l'objet
	 */
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "patronyme", length = 100)
	private String nom;
	private String prenom;
	private int age;
	private String email;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public String getPrenom() { return prenom; }
	public void setPrenom(String prenom) { this.prenom = prenom; }
	public int getAge() { return age; }
	public void setAge(int age) { this.age = age; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public Client() {}
	public Client(int id, String nom, String prenom, int age, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", email=" + email + "]";
	}
	
	

}
