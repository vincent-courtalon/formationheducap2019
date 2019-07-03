package exercice2BForm;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("somme initiale? ");
		double sommeInitiale = Double.parseDouble(lecteur.nextLine());
		System.out.println("somme a atteindre? ");
		double sommeObjectif = Double.parseDouble(lecteur.nextLine());
		System.out.println("taux (exemple 0.05 pour 5%)? ");
		double taux = Double.parseDouble(lecteur.nextLine());
		
		int annee = calculDuree(sommeInitiale, taux, sommeObjectif);
		System.out.println("il faudra " + annee + " années");

	}
	
	public static int calculDuree(double sommeInitiale,
								  double taux,	// 5% -> 0.05
								  double sommeObjectif) {
		if (taux <= 0)
			return -1; // pas possible
		int annee = 0;
		while (sommeInitiale < sommeObjectif) {
			annee++;
			sommeInitiale = sommeInitiale + (sommeInitiale * taux );
		}
		return annee;
	}

}
