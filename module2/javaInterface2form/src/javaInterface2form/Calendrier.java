package javaInterface2form;

import java.time.LocalDate;
import java.util.Iterator;

public class Calendrier implements Iterable<LocalDate> {
	private LocalDate debut;
	private LocalDate fin;
	
	public LocalDate getDebut() {return debut;}
	public void setDebut(LocalDate debut) {this.debut = debut;}
	public LocalDate getFin() {return fin;}
	public void setFin(LocalDate fin) {this.fin = fin;}
	
	public Calendrier(LocalDate debut, LocalDate fin) {
		this.debut = debut;
		this.fin = fin;
	}
	
	@Override
	public String toString() {
		return "Calendrier [debut=" + debut + ", fin=" + fin + "]";
	}
	
	@Override
	public Iterator<LocalDate> iterator() {
		return new CalendrierIterator(); // curseur de parcours de ma collection
	}
	
	// classe interne
	public class CalendrierIterator implements  Iterator<LocalDate>{
		private LocalDate currentDay;

		public CalendrierIterator() {
			// une classe interne a access au attributs même privé de sa classe
			// externe
			this.currentDay = debut;
		}

		@Override
		public boolean hasNext() {
			// est on avant la fin ou sur le jour de fin lui-même (avant fin + 1)
			return currentDay.isBefore(fin.plusDays(1));
		}

		// renvoie le jour actuel et passe au suivant
		@Override
		public LocalDate next() {
			LocalDate current = currentDay; // je renvoie le jours courant
			this.currentDay = currentDay.plusDays(1); // je passe au jour suivant
			return current;
		}
		
	}

}
