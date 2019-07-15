package javainterfacesForm;

public class CompteLCL implements Compte {

	private double solde;
	private String iban;
	
	public CompteLCL(double solde, String iban) {
		super();
		this.solde = solde;
		this.iban = iban;
	}
	@Override
	public String toString() {
		return "CompteLCL [solde=" + solde + ", iban=" + iban + "]";
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
