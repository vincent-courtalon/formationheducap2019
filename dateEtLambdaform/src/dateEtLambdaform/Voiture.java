package dateEtLambdaform;

import java.time.LocalDate;

public class Voiture extends Object implements Comparable<Voiture>
{
	private String nom;
	private int puissanceChevaux;
	private String couleur;
	private LocalDate dateConstruction;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public int getPuissanceChevaux() {return puissanceChevaux;}
	public void setPuissanceChevaux(int puissanceChevaux) {this.puissanceChevaux = puissanceChevaux;}
	public String getCouleur() {return couleur;}
	public void setCouleur(String couleur) {this.couleur = couleur;}
	public LocalDate getDateConstruction() {return dateConstruction;}
	public void setDateConstruction(LocalDate dateConstruction) {this.dateConstruction = dateConstruction;}

	public Voiture() {}
	public Voiture(String nom, int puissanceChevaux, String couleur, LocalDate dateConstruction) {
		this.nom = nom;
		this.puissanceChevaux = puissanceChevaux;
		this.couleur = couleur;
		this.dateConstruction = dateConstruction;
	}
	
	@Override
	public String toString() {
		return "Voiture [nom=" + nom + ", puissanceChevaux=" + puissanceChevaux + ", couleur=" + couleur
				+ ", dateConstruction=" + dateConstruction + "]";
	}
	// permet de comparer deux objet entre eux
	// pour définir une relation d'ordre, plus petit, plus grand ou egal
	// positif -> plus grand
	// negatif -> plus petit
	// 0	   -> egal
	@Override
	public int compareTo(Voiture o) {
		// je compare mon nom au nom de l'autre voiture
		return this.getNom().compareTo(o.getNom());
	}
	
	

}
