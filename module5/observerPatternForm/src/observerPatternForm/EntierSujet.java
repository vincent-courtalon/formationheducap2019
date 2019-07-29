package observerPatternForm;

import java.util.ArrayList;
import java.util.List;

public class EntierSujet {
	private List<EntierObserver> observateurs;
	
	public EntierSujet() {
		this.observateurs = new ArrayList<EntierObserver>();
	}
	
	//  s'attacher/observer le sujet
	public void addObserver(EntierObserver observateur) {
		this.observateurs.add(observateur);
	}
	
	public void publierEntier(int entier) {
		// je notifie ceux qui m'observe d'une nouvelle valeur
		for (EntierObserver observateur : observateurs)
			observateur.update(entier);
	}
	
}
