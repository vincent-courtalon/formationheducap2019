package multithread3form;

import java.util.ArrayList;
import java.util.Random;

public class TravailAttente {
	
	// ce tableau contient un ensemble de valeur de fibonacci a calculer
	private ArrayList<Long> calculFibonacci;
	
	public TravailAttente(int nbTravaux) {
		calculFibonacci = new ArrayList<>();
		Random rd = new Random();
		for (int i = 0; i < nbTravaux; i++) {
			calculFibonacci.add(42L /*(long)(rd.nextInt(7) + 38)*/);
		}
	}

	public synchronized long getNextValue() {
		if (calculFibonacci.isEmpty())
			throw new RuntimeException("no more work!");
		// on recupere le dernier de la liste
		long value = calculFibonacci.remove(calculFibonacci.size() - 1);
		return value;
	}
	

}
