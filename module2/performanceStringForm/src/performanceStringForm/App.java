package performanceStringForm;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner lecteur = new Scanner(System.in);
		
		System.out.println("appuyez sur entree pour démarrer");
		lecteur.nextLine();
		long debut = System.currentTimeMillis();
		
		String buffer = "";
		for (int i = 0; i < 200000; i++) {
			buffer += "ho";
			if (i % 1000 == 0) {
				System.out.println("iteration no " + i);
			}
		}
		long fin = System.currentTimeMillis();
		System.out.println("terminé en " + ((fin - debut) / 1000.0) + " secondes");
		
		System.out.println("appuyez sur entree pour démarrer");
		lecteur.nextLine();
		
		debut = System.currentTimeMillis();
		// la classe StringBuilder est spécialement dédiée
		// au manipulation de chaines massives et répétées
		StringBuilder sb = new StringBuilder(); 
		for (int i = 0; i < 200000; i++) {
			sb.append("ho"); // equivalent au += de String
			if (i % 1000 == 0) {
				System.out.println("iteration no " + i + " - " + sb.capacity());
			}
		}
		buffer = sb.toString();
		fin = System.currentTimeMillis();
		System.out.println("terminé en " + ((fin - debut) / 1000.0) + " secondes");
		
	}

}
