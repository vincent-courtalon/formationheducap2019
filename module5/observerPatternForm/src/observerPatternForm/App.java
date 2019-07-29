package observerPatternForm;

import java.io.File;
import java.util.Random;

public class App {

	public static void main(String[] args) {
		
		// source d'entiers
		EntierSujet randomSujet = new EntierSujet();
		
		AfficheurDecimal aff01 = new AfficheurDecimal(randomSujet);
		AfficheurHexadecimal aff02 = new AfficheurHexadecimal(randomSujet);
		FileAfficheur aff03 = new FileAfficheur(randomSujet, new File("sortie.txt"));
		
		Random rd = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println("publication no " + i);
			randomSujet.publierEntier(rd.nextInt(10000));
		}
		
		aff03.close();

	}

}
