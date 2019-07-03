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
		
	}
	
	public static void calendrier_header(int annee, int mois) {
		
	}
	
	public static String nomMois(int mois) {
		return "";
	}
}
