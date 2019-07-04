package penduForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		
		Random rd = new Random();
		
		ArrayList<String> listemots = chargerMots();
		
		// tirer un mot au hazard
		String motChoisi = listemots.get(rd.nextInt(listemots.size()));
		int nbEssaisRestant = 10;
		char[] lettre_trouvee = new char[motChoisi.length()];
		Arrays.fill(lettre_trouvee, '_');
		Scanner reader = new Scanner(System.in);
		
		while(nbEssaisRestant > 0) {
			afficherEtat(nbEssaisRestant, lettre_trouvee);
			char c = reader.nextLine().toUpperCase().charAt(0);
			if (!mettre_a_jour_lettre(c, lettre_trouvee, motChoisi)) {
				nbEssaisRestant--;
				System.out.println("lettre non présente!");
			}
			else {
				System.out.println("lettre présente!");
			}
			if (motChoisi.equals(new String(lettre_trouvee))) {
				break;
			}
		}
		if (nbEssaisRestant > 0) {
			System.out.println("gagné!!!, le mot était :" + motChoisi);
		}
		else {
			System.out.println("perdu, le mot était :" + motChoisi);
		}
		
		
	}
	
	public static boolean mettre_a_jour_lettre(char c, char[] lettre_trouvee, String mot) {
		boolean found = false;
		for (int i = 0; i < mot.length(); i++) {
			if (mot.charAt(i) == c) {
				lettre_trouvee[i] = c;
				found = true;
			}
		}
		return found;
	}
	
	
	public static void afficherEtat(int nbEssaisRestant, char[] lettre_trouvee) {
		System.out.println("il vous reste " + nbEssaisRestant + " essais");
		System.out.print("lettres trouvée: ");
		for(char c : lettre_trouvee) {
			System.out.print(c);
		}
		System.out.println();
		System.out.println("votre choix ? ");
	}
	
	public static ArrayList<String> chargerMots() throws FileNotFoundException {
		Scanner filereader = new Scanner(new File("mots.txt"));
		ArrayList<String> listemots = new ArrayList<>();
		
		while(filereader.hasNext()) {
			listemots.add(filereader.nextLine().trim());
		}
		
		return listemots;
	}

}
