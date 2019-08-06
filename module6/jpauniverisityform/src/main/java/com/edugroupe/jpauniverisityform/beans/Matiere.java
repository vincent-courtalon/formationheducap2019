package com.edugroupe.jpauniverisityform.beans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;

@Entity
public class Matiere {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)		private int id;
																private String libelle;
	@OneToMany( mappedBy = "matiere")							private Set<Cours> courses;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getLibelle() { return libelle; }
	public void setLibelle(String libelle) { this.libelle = libelle; }
	public Set<Cours> getCourses() {
		if (courses == null)
			courses = new HashSet<>();
		return courses; 
	}
	public void setCourses(Set<Cours> courses) { this.courses = courses; }
	
	public Matiere() {}
	public Matiere(int id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}
	
	@Override
	public String toString() {
		return "Matiere [id=" + id + ", libelle=" + libelle + "]";
	}

	
	
}
