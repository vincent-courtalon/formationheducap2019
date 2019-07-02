package exercice1CForm;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		System.out.println("taille du triangle?");
		Scanner lecteur = new Scanner(System.in);
		int taille = Integer.parseInt(lecteur.nextLine());
		
		for (int ligne = 0; ligne < taille; ligne++) {
			for (int colonne = 0; colonne < taille - ligne; colonne++) {
				// est ce un caracter de bord
				if (ligne == 0 || colonne == 0 || colonne == taille - ligne - 1 )
					System.out.print("*");
				else 
					System.out.print("-");
			}
			System.out.println();
		}
		
		System.out.println("taille du losange?");
		taille = Integer.parseInt(lecteur.nextLine());
		
		
		int demitaille = taille / 2;
		String losange_haut = "";
		String losange_bas = "";
		for(int ligne = 0; ligne <= demitaille; ligne++) {
			String texte_ligne = "";
			// affichage retrait/marge
			for(int marge = 0; marge < demitaille - ligne; marge++) {
				texte_ligne += " ";
			}
			// affichage de la ligne du losange
			for (int colonne = 0; colonne < ligne * 2 + 1; colonne++) {
				if (colonne == 0 || colonne ==  ligne * 2)
					texte_ligne += "*";
				else
					texte_ligne += "-";
			}
			texte_ligne += "\n";
			// la ligne courante est ajouté au deux parties du losanges
			losange_haut += texte_ligne;
			// sauf si c'est le ligne centrale
			if (ligne < demitaille)
				losange_bas = texte_ligne + losange_bas;
		}
		System.out.println(losange_haut + losange_bas);
	}

}
