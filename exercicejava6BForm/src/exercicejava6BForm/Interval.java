package exercicejava6BForm;

import java.util.Iterator;

public class Interval implements Iterable<Integer>, Comparable<Interval>
{
	private int debut;
	private int fin;
	
	public int getDebut() {return debut;}
	public void setDebut(int debut) {this.debut = debut;}
	public int getFin() {return fin;}
	public void setFin(int fin) {this.fin = fin;}
	
	public Interval(int debut, int fin) {
		this.debut = debut;
		this.fin = fin;
	}
	
	@Override
	public String toString() {
		return "Interval [debut=" + debut + ", fin=" + fin + "]";
	}
	@Override
	public Iterator<Integer> iterator() {
		return new IntervalIterator();
	}
	
	/*
	 * classe interne curseur, (iterator) 
	 * permet le parcour de l'interval
	 */
	public class IntervalIterator implements Iterator<Integer> {
		private int current;
		
		public IntervalIterator() {
			current = debut; // position sur debut au démarrage
		}
		
		@Override
		public boolean hasNext() {
			return current <= fin; // finir quand on dépasse la fin de l'interval
		}

		@Override
		public Integer next() {
			return current++;// renvoie current, puis passe au suivant
		}
		
	}

	@Override
	public int compareTo(Interval o) {
		return Integer.compare(Math.abs(fin - debut), Math.abs(o.fin - o.debut));
	}
	

}
