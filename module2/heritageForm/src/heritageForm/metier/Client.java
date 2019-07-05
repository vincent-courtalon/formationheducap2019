package heritageForm.metier;

import java.time.LocalDate;

public class Client extends Personne {
	private String noCompte;
	private LocalDate dateContrat;
	
	public String getNoCompte() {return noCompte;}
	public void setNoCompte(String noCompte) {this.noCompte = noCompte;}
	public LocalDate getDateContrat() {return dateContrat;}
	public void setDateContrat(LocalDate dateContrat) {this.dateContrat = dateContrat;}
	
	public Client() {}
	public Client(int id, String nom, String prenom, LocalDate dateNaissance, String noCompte, LocalDate dateContrat) {
		super(id, nom, prenom, dateNaissance);
		setNoCompte(noCompte);
		setDateContrat(dateContrat);
	}
	@Override
	public String toString() {
		return "Client [noCompte=" + noCompte + ", dateContrat=" + dateContrat + "] "
				+ super.toString();
	}
	
	@Override
	public String saveToCsv() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.saveToCsv())
		  .append(',')
		  .append(getNoCompte())
		  .append(',')
		  .append(getDateContrat());
		return sb.toString();
	}
	
	@Override
	public String description() {
		return getNom() + " enregistré le " + getDateContrat();
	}
	
	
	
	
	
	

}
