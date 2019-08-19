package com.edugroupe.springKamelotForm.beans;

import java.time.LocalDateTime;

public class Cheval {
	private String nom;
	private LocalDateTime dateNaissance;
	
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public LocalDateTime getDateNaissance() { return dateNaissance; }
	public void setDateNaissance(LocalDateTime dateNaissance) { this.dateNaissance = dateNaissance; }
	
	public Cheval() {
		setDateNaissance(LocalDateTime.now());
		System.out.println("construction d'un cheval");
	}
	
	@Override
	public String toString() {
		return "Cheval [nom=" + nom + ", dateNaissance=" + dateNaissance + "]";
	}

}
