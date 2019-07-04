package exercice3Form;

import exercice3Form.metier.Article;
import exercice3Form.metier.Materiel;

public class App {

	public static void main(String[] args) {
		Article a1 = new Article("imprimante HP 4500", "pour les impressions laser importantes", "imprimantes");
		Article a2 = new Article("Hyama 3200 21 pouces", "pour les graphistes", "ecrans");
		
		Materiel m1 = a1.createMateriel("123456789", 3, "edugroupe malakof");
		Materiel m2 = a2.createMateriel("687654654", 5, "2 rue du graphisme");
		Materiel m3 = a1.createMateriel("123456789", 3, "2 rue du graphisme");
		Materiel m4 = a1.createMateriel("453678971", 1, "2 rue du graphisme");
		
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m4);
		
		System.out.println("m1 equals m2 -> " + m1.equals(m2));
		System.out.println("m1 equals m3 -> " + m1.equals(m3));
		System.out.println("m1 equals m4 -> " + m1.equals(m4));


	}

}
