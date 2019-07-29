package observerPatternForm;

public class AfficheurHexadecimal implements EntierObserver {
	private EntierSujet sujet;
	
	
	
	public AfficheurHexadecimal(EntierSujet sujet) {
		this.sujet = sujet;
		this.sujet.addObserver(this);
	}

	@Override
	public void update(int entier) {
		System.out.println("entier(hexa): " + Integer.toHexString(entier));
	}

}
