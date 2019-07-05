package heritageForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import heritageForm.metier.Client;
import heritageForm.metier.Employe;
import heritageForm.metier.Personne;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<Personne> peoples = new ArrayList<>();
		
		Random rd = new Random();
		for (int i = 1; i <= 10; i++) {
			if (rd.nextBoolean()) {
				peoples.add(new Client(i, "eponge" + i,
						"bob" + i, 
						LocalDate.of(rd.nextInt(20) + 1980, 7, 5),
						"46735167" + i,
						LocalDate.of(rd.nextInt(5) + 2010, 1, 1)));
				/*peoples.add(new Personne(i, "eponge" + i,
						"bob" + i,
						LocalDate.of(rd.nextInt(20) + 1980, 7, 5)));*/
			}
			else {
				peoples.add(new Employe(i, "starfish" + i,
						"patrick" + i,
						LocalDate.of(rd.nextInt(20) + 1980, 7, 5),
						"accueil",
						rd.nextDouble() * 1000.0 + 1000.0));
			}
		}
		
		for (Personne p : peoples) {
			System.out.println(p.description());
		}
		
		File repertoire = new File("repertoire.csv");
		PrintWriter pw = new PrintWriter(repertoire);
		for (Personne p : peoples) {
			pw.println(p.saveToCsv());
		}
		// ATTENTION!!, en ecriture, il est absolument nécéssaire
		// de fermer (ou flusher) le printwriter a la fin
		// sinon, le fichier risque de ne pas etre ecrit ou partiellement
		pw.close();

		/*Personne p = new Personne(1, "eponge" + 1,
				"bob" + 1,
				LocalDate.of(rd.nextInt(20) + 1980, 7, 5));*/
	}

}
