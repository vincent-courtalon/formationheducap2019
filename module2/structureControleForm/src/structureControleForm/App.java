package structureControleForm;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		/*
		// les structures de controles, ce sont les elements du langage
		// permettant de faire des choix/branchement, ou des répétitions (boucles)
		
		// comment demander une saisie à l'utilisateur
		// System.out -> sortie standard
		// System.in -> entree standard
		// le scanner est un objet, new pour l'instancier
		Scanner lecteur = new Scanner(System.in);
		System.out.println("quelle heure est il ?");
		// récupération saisie
		String saisie = lecteur.nextLine();
		
		// conversion chaine -> chiffre
		int heure = Integer.parseInt(saisie);
		
		if (heure <= 10 ) {
			System.out.println("bonjour, un café?");
		}
		else if (heure <= 12) {
			System.out.println("ou sont les madeleines?");
		}
		else {
			System.out.println("bon appétit");
		}
		// == -> comparaison d'égalité
		// != -> différent
		if (heure == 17) {
			System.out.println("bon retour!");
		}

		System.out.println("code de votre pays ? ");
		String code = lecteur.nextLine().toLowerCase();
		// le switch est possible sur les chaines de caracteres
		// depuis java 7
		switch(code) {
			case "be":
				System.out.println("flamand");
			case "fr":
				System.out.println("francais");
				break;
			case "uk":
			case "us":
				System.out.println("anglais");
				break;
			case "es":
				System.out.println("espagnol");
				break;
			case "de":
				System.out.println("allemand");
				break;
			default:
				System.out.println("esperanto");
				break;
		}
		
		System.out.println("quelle est votre age?");
		int age = Integer.parseInt(lecteur.nextLine());
		// operateur ternaire
		age = (age >= 0)? age : 0;
		System.out.println("votre age : " +age);
		*/
		// les boucles
		
		int compteur = 0;
		while(compteur < 10) {
			if (compteur == 6) {
				break;
			}
			System.out.println("compteur = " + compteur);
			compteur++; // postincrement --> compteur +=1  --> compteur = compteur + 1
		}
		
		compteur = 15;
		do {
			System.out.println("compteur = " + compteur);
			compteur++;
		}while(compteur < 10);
		
		// boucle for
		System.out.println("-----------------------------------");
		// for (initialisation; test d'arret; mise a jour/iteration)
		for (int k=1; k < 12; k++) {
			if (k == 9)
				break;
			if (k == 4)
				continue; // saute a la prochaine itération
			System.out.println("k = " + k);
		}
		
		//k = 6;
		
		String[] jours = {"lundi", "mardi", "mercredi", "jeudi", "vendredi"};
		
		// pour chaque chaine de caractere dans le tableau jours
		// executer le code avec la chaine de caractere dans j
		for(String j : jours) {
			System.out.println("jour = " + j);
		}
		
		
	}

}
