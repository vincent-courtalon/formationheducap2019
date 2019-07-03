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
		//System.out.println(nbJoursMois(annee, mois));
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
