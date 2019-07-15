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
		
		ArrayList<String> plats = new ArrayList<>();
		plats.add("poulet frites");
		plats.add("saumon riz");
		plats.add("paella savoyarde");
		
		//System.out.println(plats);
		Collections.sort(plats);
		for (String p : plats)
			System.out.println(p);
		
		Comparable<String> cp2 = "bonjour";
		System.out.println("au revoir".compareTo("bonjour"));
		
		
		ArrayList<Compte> comptes = new ArrayList<>();
		comptes.add(new CompteSG(1500, "123456789"));
		comptes.add(new CompteSG(2500, "123456789"));
		comptes.add(new CompteSG(1200, "123456789"));
		System.out.println("------------------------------");
		for (Compte c : comptes)
			System.out.println(c);
		System.out.println("------------------------------");
		for (Compte c : comptes)
			c.deposer(250);
		System.out.println("------------------------------");
		for (Compte c : comptes)
			System.out.println(c);
		
		CompteSG csg1 = new CompteSG(500, "123456789");
		CompteLCL clcl1 = new CompteLCL(300, "879434676");
		System.out.println("------------------------------");
		System.out.println(csg1);
		System.out.println(clcl1);
		transfert(csg1, clcl1, 150);
		System.out.println("------------------------------");
		System.out.println(csg1);
		System.out.println(clcl1);
		
	}
	
	// cette fonction travail sur l'interface compte
	// elle marchera pour toutes classe qui implemente cette interface
	//  pas besoin de connaitre le type concret du compte
	public static boolean transfert(Compte source, Compte destination, double somme) {
		if (source.retirer(somme)) {
			destination.deposer(somme);
			return true;
		}
		return false;
	}

	
	
	
}
