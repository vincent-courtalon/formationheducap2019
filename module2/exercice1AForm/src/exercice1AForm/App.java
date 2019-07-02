package exercice1AForm;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		int nb_notes = 0;
		double somme = 0.0;
		Scanner reader = new Scanner(System.in);
		
		while(true) {
			System.out.println("saisissez une note (<0 pour finir)");
			int note = Integer.parseInt(reader.nextLine());
			if (note < 0)
				break;
			somme += note;
			nb_notes++;
		}
		
		if (nb_notes > 0)
			System.out.println("moyenne = " + (somme / nb_notes));
		else
			System.out.println("pas de notes saisies");

	}

}
