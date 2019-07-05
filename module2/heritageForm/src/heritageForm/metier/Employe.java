package heritageForm.metier;

import java.time.LocalDate;

// interdire heritage d'employe
//public final class Employe extends Personne
public class Employe extends Personne
{
	private String poste;
	private double salaire;
	
	public String getPoste() {return poste;}
	public void setPoste(String poste) {this.poste = poste;}
	public double getSalaire() {return salaire;}
	public void setSalaire(double salaire) {this.salaire = salaire;}
	
	
	
	public Employe() {
		super();
	}
	public Employe(int id, String nom, String prenom, LocalDate dateNaissance, String poste, double salaire) {
		super(id, nom, prenom, dateNaissance);
		setPoste(poste);
		setSalaire(salaire);
		
	}
	
	@Override
	public final String toString() {
		// super.methode permet de rappeler explicitement un méthode héritée
		// même si on l'a overridé à notre niveau
		// on ne peut pas courcircuiter notre parent super.super pas possible
		// on ne peut pas, de l'exterieur de la classe, courcircuiter celle-ci
		
		return "Employe [poste=" + poste + ", salaire=" + salaire + "] "
					+  super.toString();
	}
	
	@Override
	public String saveToCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.saveToCsv())
		  .append(',')
		  .append(getPoste())
		  .append(',')
		  .append(getSalaire());
		return sb.toString();
	}
	
	@Override
	public String description() {
		return getNom() + " payé " + getSalaire();
	}
	
	
	
}
