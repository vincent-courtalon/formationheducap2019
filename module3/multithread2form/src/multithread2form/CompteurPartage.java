package multithread2form;

import java.util.concurrent.atomic.AtomicLong;

public class CompteurPartage {
	
	private long  compteur;
	private AtomicLong compteurAtomic;
	

	public long getCompteur() { return compteur; }
	public void setCompteur(long compteur) { this.compteur = compteur; }
	
	public CompteurPartage(long compteur) {
		this.compteur = compteur;
		this.compteurAtomic = new AtomicLong(compteur);
	}
	
	// synchronized denote une methode ou un bloc de code critique
	// c.a.d qui ne doit etre executer que par un Thread à la fois
	// si un autre thread veu l'executer alors qu'elle est déjà en cours d'execution
	// il devra patienter pour avoir son tour
	public synchronized long incrementCompteur() {
		this.compteur = this.compteur + 1;
		return this.compteur;
	}
	
	public long incrementCompteurOptimise() {
		return this.compteurAtomic.incrementAndGet();
	}
	
	@Override
	public String toString() {
		return "CompteurPartage [compteur=" + compteur + ", compteurAtomic=" + compteurAtomic + "]";
	}
	


}
