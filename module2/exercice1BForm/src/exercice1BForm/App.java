package exercice1BForm;

import java.util.Arrays;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		System.out.println("saisissez un mot a tester:");
		Scanner lecteur = new Scanner(System.in);
		String mot = lecteur.nextLine();
		
		int debut = 0;
		int fin = mot.length() - 1;
		boolean estPalindrome = true;
		
		while (debut < fin) {
			if (mot.charAt(debut) != mot.charAt(fin))
			{
				estPalindrome = false;
				break;
			}
			debut++;
			fin--;
		}
		if (estPalindrome)
			System.out.println("c'est un palindrome");
		else
			System.out.println("ce n'est pas un palindrome");
		
	}

}
