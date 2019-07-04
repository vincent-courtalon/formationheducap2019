package objetDebutForm;

import java.io.File;
import java.util.Scanner;

import objetDebutForm.metier.Livre;
import objetDebutForm.metier.Vecteur;

public class App {

	public static void main(String[] args) {

	/*	File f = new File("toto");

		Scanner lecteur = new Scanner(f);
		while (lecteur.hasNext()) {
			String[] mots = lecteur.nextLine().split(",");
			
		}
		*/
	/*	Livre l1; // livre non instancié -->null
		l1 = new Livre();
		// pour accéder aux attributs d'un objet, utiliser '.' 
		l1.id = 1;
		l1.titre = "ghost in the shell";
		l1.auteur = "Masamune shirow";
		l1.setNbPages(-200);
		l1.setPrix(-9.99);
		
		// pour appeler une méthode, utiliser '.'
		System.out.println(l1.description());
		System.out.println(l1.getPrix());
		
		
		Livre l2 = new Livre();
		l2.id = 2;
		System.out.println(l2.description());
		
		Livre l3 = new Livre(3, "dragon ball", "akira toriyama", 120, 8.99);
		System.out.println(l3.description());
		*/
		
		System.out.println("nb vecteur creer " + Vecteur.getNbVecteurCree());
		
		Vecteur v1 = new Vecteur(1.0, 5.0, -2.0);
		Vecteur v2 = new Vecteur(2.0, 4.0, 0.5);
		
		System.out.println(v1);
		System.out.println(v2);
		
		v1.add(v2);
		System.out.println(v1);
		
		Vecteur v4 = new Vecteur(v2);
		System.out.println("v2 = " + v2);
		System.out.println("v4 = " + v4);
		Vecteur v5 = v2; // v5 et v2 référence la même instance de vecteur
		
		System.out.println("v2 == v5 -> " + (v2 == v5));
		System.out.println("v2 == v4 -> " + (v2 == v4));
		System.out.println("v2 == v1 -> " + (v2 == v1));
		
		System.out.println("v2 equals v5 -> " + (v2.equals(v5)));
		System.out.println("v2 equals v4 -> " + (v2.equals(v4)));
		System.out.println("v2 equals v1 -> " + (v2.equals(v1)));
		System.out.println("nb vecteur creer " + Vecteur.getNbVecteurCree());
		
	}

}
