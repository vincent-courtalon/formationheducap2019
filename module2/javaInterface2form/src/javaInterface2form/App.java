package javaInterface2form;

import java.time.LocalDate;
import java.util.Iterator;

import javaInterface2form.Calendrier.CalendrierIterator;

public class App {

	public static void main(String[] args) {
		
		Calendrier c1 = new Calendrier(	LocalDate.of(2019, 7, 1),
										LocalDate.of(2019, 8, 1));
		
		System.out.println(c1);
		
		Iterator<LocalDate> ci = c1.iterator();
		while (ci.hasNext()) {
			LocalDate jour = ci.next(); 
			System.out.println(jour);
		}
		
		//c1.forEach(action);
		
		/*
		for (LocalDate jour : c1) {
			System.out.println(jour);
		}
		*/
		
		
	}

}
