package heritageForm.metier;

import java.time.LocalDate;

// la classe Personne est abstraite
// non instantiable
public abstract class Personne {
	// id sera accessible directement
	// par mes descendants et les classes dans le même package
	protected int id;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getPrenom() {return prenom;}
	public void setPrenom(String prenom) {this.prenom = prenom;}
	public LocalDate getDateNaissance() {return dateNaissance;}
	public void setDateNaissance(LocalDate dateNaissance) {this.dateNaissance = dateNaissance;}

	
	public Personne() {}
	public Personne(int id, String nom, String prenom, LocalDate dateNaissance) {
		setId(id);
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
	}
	
	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + "]";
	}
	
	public String saveToCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(getId()).append(',')
							.append(getNom())
							.append(',')
							.append(getPrenom())
							.append(',')
							.append(getDateNaissance());
		return sb.toString();
	}
	
	// méthode abstraite, sans implémentation
	public abstract String description();
	

}
