package heritageForm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import heritageForm.metier.Employe;
import heritageForm.metier.Personne;

public class App {

	public static void main(String[] args) {
		ArrayList<Personne> peoples = new ArrayList<>();
		
		Random rd = new Random();
		for (int i = 1; i <= 10; i++) {
			if (rd.nextBoolean()) {
				peoples.add(new Personne(i, "eponge" + i,
						"bob" + i,
						LocalDate.of(rd.nextInt(20) + 1980, 7, 5)));
			}
			else {
				peoples.add(new Employe(i, "starfish" + i,
						"patrick" + i,
						LocalDate.of(rd.nextInt(20) + 1980, 7, 5),
						"accueil",
						rd.nextDouble() * 1000.0 + 1000.0));
			}
		}
		
		for (Personne p : peoples)
			System.out.println(p);

	}

}
