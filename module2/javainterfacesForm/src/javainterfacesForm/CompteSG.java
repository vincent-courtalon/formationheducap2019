package javainterfacesForm;

public class CompteSG implements Compte
{
	private double solde;
	private String noCompte;
	
	public CompteSG(double solde, String noCompte) {
		this.solde = solde;
		this.noCompte = noCompte;
	}
	
	@Override
	public String toString() {
		return "CompteSG [solde=" + solde + ", noCompte=" + noCompte + "]";
	}

	@Override
	public void deposer(double montant) {
		this.solde += montant;
	}

	@Override
	public boolean retirer(double montant) {
		if (montant <= this.solde) {
			this.solde -= montant;
			return true;
		}
		return false;
	}

	@Override
	public double getSolde() {
		return solde;
	}
	

}
