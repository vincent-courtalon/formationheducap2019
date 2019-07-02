package helloWorldForm;

import java.util.Arrays;

public class App {

	// point d'entrée du programme java
	public static void main(String[] args) {
		
		// le langage java est inpératif, suite d'instruction
		// séparées par des ';'
		// java est sensible à la casse ! Toto <> toto
		
		// les types de vairables/données en java
		// en java, langage fortement typés
		// toutes les variables on un type à la déclaration
		
		// numériques
		// un entier 32 bits signé contenant 15
		// -2 milliard a + 2 milliard : -2^31  <-> 2^31 - 1
		int entierA = 15;
		System.out.println(entierA);
		entierA = entierA + 12;
		System.out.println(entierA);
		
		// entier 64 bits -> long : -2^63 <-> 2^63 -1
		long entierB = 127;
		System.out.println(entierB);
		
		//les entiers litteraux sont par defaut 32 bits
		// utiliser le suffix L pour specifier un litteral 64 bits
		entierB = 123456789123456789L;
		System.out.println(entierB);
		
		// la conversion 32 bits -> 64 bits est implicite (automatique) 
		entierB = entierA;
		
		// dans le sens inverse, ce n'est pas le cas
		// la conversion est possible, mais doit être explicite
		entierA = (int)entierB;
		System.out.println(entierA);
		
		// entier 16 bits (32767  -32768)
		short entierC = 32767;
		System.out.println(entierC);
		
		// quand une conversion marche a tout les coups
		// plus petit -> plus grand, pas besoin de cast, implicite
		// dans le cas inverse, cas nécéssaire
		
		entierA = entierC;
		
		// l'entier 8bits ou octet -> byte 127 -128
		byte entierD = 125;
		System.out.println(entierD);
		
		// java n'a pas d'entier non signé
		
		// numerique a virgule flottante
		// double flottant 64 bits
		// les litteraux flottant sont 64bits par defaut
		double flottantA = 3.14151617181920212223;
		System.out.println(flottantA);
		
		// le flottant 32 bits -> float
		float flottantB = 3.14151617181920212223F;
		System.out.println(flottantB);
		
		// le type caractere unicode 
		char c1 = 'e';
		System.out.println(c1);
		
		boolean b1 = true;
		boolean b2 = false;
		System.out.println("b1 = " + b1);
		System.out.println("b2 = " + b2);
		System.out.println("b1 ou b2 = " + (b1 || b2));
		System.out.println("b1 et b2 = " + (b1 && b2));
		System.out.println("not b1 = " + !b1);
		
		// chaine de caractere
		String str1 = "bonjour monde";
		System.out.println(str1);
		
		// vous avez deux grand types de variable en java
		// les types "valeurs"
		// les types "référence"
		// les types valeurs sont les types simples
		// qui tiennent directement dans une cas mémoire/registre
		// en java -> type valeur = minuscule
		// vous ne pouvez pas déclarer vos propres types valeurs personnalisé
		// avantage des types valeurs -> performance, pas d'allocation mémoire
		
		// tous le reste est type référence (un pointeur vers un objet/tableau)
		
		// tableau d'entier  
		int[] tabA = {10, 20};
		int[] tabB; // tableau non alloué

		System.out.println(Arrays.toString(tabA));
		tabB = tabA; // ce n'est pas une copie tabB et tabA référence le même tableau
		tabA[0] = 15; 

		System.out.println(Arrays.toString(tabB));
		
		// en java, pour acceder aux attributs ou méthodes
		// d'un objet (type référence), utiliser '.'
		System.out.println("longueur str1 = " + str1.length());
		// quelques autres manipulations de chaines
		System.out.println("acceder au caractere à la position 2 de la chaine: "
							+ str1.charAt(2));
		System.out.println(str1);
		// extraire sous chaine (position debut, position fin exclue)
		System.out.println("sous chaine : " + str1.substring(3, 5));
		
		
		
	}

}
