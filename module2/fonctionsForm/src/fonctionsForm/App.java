package fonctionsForm;

import java.util.Scanner;

public class App {

	
	// visibilité    --> public
	// modificateur   --> static
	// donnée_renvoyée --> void
	// nom fonction --> main
	// (liste d'arguments) --> String[] args
	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("votre nom?");
		String saisie = lecteur.nextLine();
		
		// quand on appele une fonction, la valeur de l'argument est copiée
		// dans la parametre de la fonction
		salutation(saisie);
		System.out.println("5.5 + 3.3 = " + addition(5.5, 2.4));
		System.out.println(saisie);
		//x = 5;
	}
	
	public static double addition(double x, double y) {
		String saisie = "hoho";
		//saisie = "hoho";
		return x + y;
	}
	
	// dans une fonction, les arguments sont disponnibles
	// comme des variables locales
	public static void salutation(String nom) {
		System.out.println("bonjour, " + nom);
	}
	

}
