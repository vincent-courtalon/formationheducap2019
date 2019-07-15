package javainterfacesForm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class App {

	public static void main(String[] args) {
		
		ArrayList<Voiture> voitures = new ArrayList<>();
		voitures.add(new Voiture("peugot 206", 80, "rouge", LocalDate.of(1998, 10, 15)));
		voitures.add(new Voiture("tesla model s", 150, "bleue", LocalDate.of(2018, 10, 15)));
		voitures.add(new Voiture("alpha romeo guilletta", 120, "blanche", LocalDate.of(2005, 10, 15)));
		voitures.add(new Voiture("nissan leaf", 100, "rouge", LocalDate.of(2010, 10, 15)));
		
		for (Voiture v : voitures)
			System.out.println(v);

		Collections.sort(voitures);
		
		System.out.println("----------------------------");
		for (Voiture v : voitures)
			System.out.println(v);
	
		// une voiture herite de l'interface comparable, donc c'est une type particulier de comparable
		Comparable<Voiture> cp1 = new Voiture("peugot 206", 80, "rouge", LocalDate.of(1998, 10, 15));
		//cp1.
		
	}

}
