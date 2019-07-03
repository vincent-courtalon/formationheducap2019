package exercice2CForm;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		System.out.println("chiffre a convertir en romain(0-999)?");
		int chiffre = Integer.parseInt(lecteur.nextLine());
		System.out.println(nombreRomain(chiffre));
		
	}

	public static String nombreRomain(int nombre) {
		// % -> modulo : reste division entiere
		int unite = nombre % 10;
		int dizaine = (nombre / 10) % 10;
		int centaine = (nombre / 100) % 10;
		return
				chiffreRomain(centaine, "C", "D", "M") +
				chiffreRomain(dizaine, "X", "L", "C") +
				chiffreRomain(unite, "I", "V", "X");
	}
	
	public static String chiffreRomain(int chiffre,
										String symbole1,
										String symbole5,
										String symbole10) {
		String result = "";
		switch(chiffre) {
			case 3: result += symbole1;
			case 2: result += symbole1;
			case 1: result += symbole1; break;
			case 4: result += symbole1 + symbole5; break;
			case 8: result += symbole1;
			case 7: result += symbole1;
			case 6: result += symbole1;
			case 5: result = symbole5 + result; break;
			case 9: result += symbole1 + symbole10; break;
		}
		return result;
	}

}
