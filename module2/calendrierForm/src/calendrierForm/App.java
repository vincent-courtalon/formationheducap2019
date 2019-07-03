package calendrierForm;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("annee ? ");
		int annee = Integer.parseInt(lecteur.nextLine());
		System.out.println("mois ? ");
		int mois = Integer.parseInt(lecteur.nextLine());
		
		calendrier(annee, mois);
	}
	
	public static void calendrier(int annee, int mois) {
		calendrier_header(annee, mois);
		calendrier_body(annee, mois);
	}
	public static void calendrier_body(int annee, int mois) {
		int jourdebut = jourDebut(annee, mois);
		int nbjoursmois = nbJoursMois(annee, mois);
		
		// boucle d'affichage
		// decalage en fonction du jour de semaine de début
		for (int marge = 0; marge < jourdebut; marge++)
			System.out.print("    ");
		
		for (int jours = 0; jours < nbjoursmois; jours++) {
			if (jours < 9)
				System.out.print(" "); // si le no jour fait 1 caractere, ajouter un espace pour alignement
			System.out.print(" " + (jours + 1) + " ");
			if ((jours + jourdebut) % 7 == 6) // si on est un dimanche, passer a la ligne
				System.out.println();
		}
		
				
	}
	
	public static int jourDebut(int annee, int mois) {
		int nbjours = 2; // on demarre un mercredi le 1er janvier 1800
		
		// année par année (boucle)
		for (int a = 1800; a < annee; a++) {
			if (estBissextile(a))
				nbjours += 366;
			else
				nbjours += 365;
		}
		// je suis sur le 1er janvier de l'année qui m'intéresse
		for (int m = 1; m < mois; m++) {
			nbjours += nbJoursMois(annee, m);
		}
		// je suis sur le bon jour, renvoyer quelle jour de la semaine on est
		
		return nbjours % 7;
	}
	
	public static void calendrier_header(int annee, int mois) {
		System.out.println("---------------------------");
		System.out.println("    "+  nomMois(mois) + " " + annee);
		System.out.println("LUN MAR MER JEU VEN SAM DIM");
	}
	
	public static String nomMois(int mois) {
		switch(mois) {
			case 1: return "janvier";
			case 2: return "fevrier";
			case 3: return "mars";
			case 4: return "avril";
			case 5: return "mai";
			case 6: return "juin";
			case 7: return "juillet";
			case 8: return "aout";
			case 9: return "septembre";
			case 10: return "octobre";
			case 11: return "novembre";
			case 12: return "decembre";
			default: return "";
		}
		
	}
	
	
	
	public static int nbJoursMois(int annee, int mois) {
		switch(mois) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: return 31;
			case 4:
			case 6:
			case 9:
			case 11: return 30;
			case 2:
				return (estBissextile(annee))? 29 : 28;
		}
		return 0;
	}
	
	public static boolean estBissextile(int annee) {
		//       tous les 4 ans masi pas les siecles  OU tous les 400 ans 
		return (annee % 4 == 0 && annee % 100 != 0) || (annee % 400 == 0);
	}
	
	
	
}
