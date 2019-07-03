package fonctionsForm;

import java.util.Arrays;
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
		
		double v1 = 4.5;
		double v2 = 8.1;
		System.out.println("v1 = " + v1 + " , v2 = " + v2);
		swap(v1, v2);
		System.out.println("v1 = " + v1 + " , v2 = " + v2);
		
		double[] data = {4.5, 8.1};
		System.out.println("datas " + Arrays.toString(data));
		swap(data);
		System.out.println("datas " + Arrays.toString(data));
		//Double db1 = 4.5;
		//System.out.println();
		System.out.println(moyenne(3.5, 12.4, 18.5, 14.2));
		
		System.out.println(factorielle(5));
	}
	
	public static long factorielle(long n) {
		if (n <= 1) {
			return 1;
		}
		else {
			return n * factorielle(n - 1);
		}
		
	}
	
	public static double moyenne(double ... values) {
		double total = 0.0;
		for (double d : values)
			total += d;
		return total / values.length;
	}
	
	// echanger deux valeurs
		public static void swap(double x, double y) {
			System.out.println("x = " + x + " , y = " + y);
			double z = x;
			x = y;
			y = z;
			System.out.println("x = " + x + " , y = " + y);
		}
		// une fonction a ce qu'on appele une signature
		// on ne peut pas avoir  2 fois la même signature
		// le type de retour ne fait pas partie de la signature
		// le nom fait paerie de la signature
		// le type et l'ordre des parametres fait partie de la signature
		public static void swap(double[] values) {
			System.out.println("dans swap " + Arrays.toString(values));
			double z = values[0];
			values[0] = values[1];
			values[1] = z;
			System.out.println("dans swap " + Arrays.toString(values));
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
