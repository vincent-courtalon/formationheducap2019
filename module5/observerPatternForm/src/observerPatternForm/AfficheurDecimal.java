package observerPatternForm;

public class AfficheurDecimal implements EntierObserver {

	private EntierSujet sujet;
	
	public AfficheurDecimal(EntierSujet sujet) {
		this.sujet = sujet;
		// je m'enregistre comme observateur du EntierSujet
		this.sujet.addObserver(this);
	}

	@Override
	public void update(int entier) {
		System.out.println("nouvel entier: " + entier);
	}

}
