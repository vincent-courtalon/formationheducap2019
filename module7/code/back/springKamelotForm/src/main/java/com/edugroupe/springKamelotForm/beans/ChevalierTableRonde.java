package com.edugroupe.springKamelotForm.beans;

public class ChevalierTableRonde implements Chevalier {
	private String nom;
	private Quete quete;
	private double competence;
	private Cheval monture; 
	
	
	public Cheval getMonture() { return monture; }
	public void setMonture(Cheval monture) { this.monture = monture; }
	@Override
	public String getNom() {return nom;}
	@Override
	public Quete getQuete() {return quete;}
	public double getCompetence() { return competence; }
	public void setCompetence(double competence) { this.competence = competence; }
	public void setNom(String nom) { this.nom = nom; }
	public void setQuete(Quete quete) { this.quete = quete; }
	
	public ChevalierTableRonde(String nom) {
		System.out.println("construction chevalier table ronde " + nom);
		this.nom = nom;
	}
	
	@Override
	public void partirEnQuete() {
		System.out.println("moi, sire " + getNom() + " part en quete sur mon fidele destrier " +
			getMonture().getNom() + " en quete: "	+ getQuete().getDescription());
		if (getQuete().realiser(getCompetence())) {
			System.out.println("moi, sire " + getNom() + " reviens victorieusement de quete");
		}
		else {
			System.out.println("moi, sire " + getNom() + " a eu un contretemps");
		}
	}
	
	@Override
	public String toString() {
		return "ChevalierTableRonde [nom=" + nom + ", quete=" + quete + ", competence=" + competence + ", monture="
				+ monture + "]";
	}
	


}
