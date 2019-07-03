package tableauxFichiersForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		// tableaux basiques en java
		// type[] -> variable tableau
		// c'est un type référence
		
		String[] villes = {"paris", "lyon", "toulouse", "bordeaux", "nantes"};
		// numéroté à partir de 0
		System.out.println(villes[2]);
		
		villes[2] = "nancy";
		// affichage de tableau
		System.out.println(Arrays.toString(villes));

		// variable tableau non initialisée
		String[] plats;
		// allouer/creer un tableau avec le mot clef new
		// attention, les tableaux basique java sont de taille fixe une fois crée
		plats = new String[4]; // tableau de 4 chaine de caracteres
		plats[0] = "poulet frites";
		plats[1] = "pizza calzone";
		plats[2] = "salade cesar";
		plats[3] = "lasagne fait maison";
		
		// parcours du tableau
		for (int pos = 0; pos < plats.length; pos++) {
			System.out.println(plats[pos]);
		}
		System.out.println(Arrays.toString(plats));
		plats = Arrays.copyOf(plats, 6);
		plats[4] = "pavé de saumon riz";
		plats[5] = "burger de lama";
		System.out.println(Arrays.toString(plats));
		
		double [][] matrice1 = {
				{4.5, 2.3, 11},
				{8.5, -2.3, 5},
				{3.3, 0.5, 7.4, 1.8}
		};
		//System.out.println(Arrays.toString(matrice1));
		System.out.println(Arrays.deepToString(matrice1));
		System.out.println(matrice1[1][2]);
		System.out.println(matrice1.length);
		System.out.println(matrice1[2].length);
		
		// allocation "manuelle" d'un tableau de double bidimensionel régulier
		double[][] matrice2 = new double[3][4];
		
		//----------------------------------------------------
		// acces au fichiers
		// la classe File permet de manipuler
		// des fichiers et répertoires
		// une instance de file représente un fichier
		File fichier = new File("noms.txt");
		if (fichier.exists()) {
			// lecteur sur le fichier
			Scanner lecteur = new Scanner(fichier);
			// tant qu'il reste quelque chose à lire
			while (lecteur.hasNext()) {
				// afficher la ligne
				System.out.println(lecteur.nextLine());
			}
			// refermer le fichier en lecture
			lecteur.close();
		}
		
		
	}

}
