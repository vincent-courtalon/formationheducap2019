package com.edugroupe.springKamelotForm.beans;

public class ChevalierBasique implements Chevalier {
	private String nom;
	private Quete quete;
	private double competence;
	
	
	@Override
	public String getNom() {return nom;}
	@Override
	public Quete getQuete() {return quete;}
	public double getCompetence() { return competence; }
	public void setCompetence(double competence) { this.competence = competence; }
	public void setNom(String nom) { this.nom = nom; }
	public void setQuete(Quete quete) { this.quete = quete; }
	
	public ChevalierBasique(String nom) {
		System.out.println("construction chevalier basique " + nom);
		this.nom = nom;
	}
	
	@Override
	public void partirEnQuete() {
		System.out.println("moi, " + getNom() + " part en quete : " + getQuete().getDescription());
		if (getQuete().realiser(getCompetence())) {
			System.out.println("moi, " + getNom() + " reviens victorieusement de quete");
		}
		else {
			System.out.println("moi, " + getNom() + " aura plus de chance une autre fois");
		}
	}
	
	@Override
	public String toString() {
		return "ChevalierBasique [nom=" + nom + ", quete=" + quete + ", competence=" + competence + "]";
	}
	
}
