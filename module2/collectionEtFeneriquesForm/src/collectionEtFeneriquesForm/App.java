package collectionEtFeneriquesForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

public class App {

	public static void main(String[] args) {
		// java inclue de nombreuse collections
		// les collections non génériques (anciennes)
		// les collections génériques ("nouvelle")
		
		// tableau dynamique -> ArrayList
		
		ArrayList tab = new ArrayList();
		// grossi automatiquement quand on ajoute des elements
		// qui réduit aussi si on en enleve
		// on y accede via un index (case numéroté)
		tab.add("fraise");
		tab.add("orange");
		tab.add("framboise");
		tab.add("pomme");
		
		for (Object fruit : tab) {
			System.out.println(fruit);
		}
		
		System.out.println("-----------------");
		System.out.println("tab[2] -> " + tab.get(2));
		
		tab.set(1, "mandarine"); // remplacer le contenu d'une case
		System.out.println("-----------------");
		for (Object fruit : tab) {
			System.out.println(fruit);
		}
		
		System.out.println("-----------------");
		tab.add(2, "poire");
		for (Object fruit : tab) {
			System.out.println(fruit);
		}
		
		System.out.println("-----------------");
		tab.remove(2);
		for (Object fruit : tab) {
			System.out.println(fruit);
		}

		//-------------------------------------------------
		System.out.println("==================================");
		
		ArrayList<String> tab2 = new ArrayList<>();
		tab2.add("brocoli");
		tab2.add("navet");
		tab2.add("petit pois");
		tab2.add("carotte");
		
		for(String legume : tab2) {
			System.out.println(legume);
		}
		
		System.out.println(tab2.get(2).length());
		
		// la "petite" limitation
		ArrayList<Integer> tab3 = new ArrayList<>();
		tab3.add(15);
		tab3.add(12);
		tab3.add(9);
		tab3.add(16);
		
		int total = 0;
		for (int note : tab3) {
			total += note;
			System.out.println("note = " + note);
		}
		System.out.println("moyenne = " + ((float)total / tab3.size()));
		
		// la pile ou stack
		
		Stack<String> pile = new Stack<>();
		
		pile.push("moutarde");
		pile.push("ketchup");
		pile.push("mayonnaise");
		pile.push("bearnaise");
		
		System.out.println("peek -> " + pile.peek()); // ne retire du dessus de la pile
		System.out.println("pop -> " + pile.pop()); // retire du dessu de la pile
		
		for (String condiment : pile)
			System.out.println(condiment);
		
		pile.push("barbecue");
		System.out.println("----------------------------------");
		for (String condiment : pile)
			System.out.println(condiment);
		
		System.out.println("---------------------------");
		// les dictionnaires ou map ou encore tableau associatif
		// une collection qui associe a une clef, une donnée
		// le type de la clef et de la donnée sont à cotre choix
		HashMap<String, Double> map1 = new HashMap<>();
		
		map1.put("patrick", 3.5);
		map1.put("bob", 7.5);
		map1.put("sandy", 17.5);
		map1.put("carlo", 12.5);
		
		System.out.println(" map1[sandy] -> " + map1.get("sandy"));
		
		System.out.println("-----------");
		for (String nom : map1.keySet()) {
			System.out.println(nom + "-->" + map1.get(nom));
		}
		System.out.println("-----------");
		
		for (Entry<String, Double> entry : map1.entrySet()) {
			//entry.
			System.out.println(entry);
		}
		
		System.out.println("-----------");
		
		Paire<String, Double> p1 = new Paire<String, Double>("fraise", 12.99);
		System.out.println(p1);
		
		Paire<Double, Integer> p2 = new Paire<Double, Integer>(3.4, 5);
		System.out.println(p2);

	}

}
