package multithread2form;

public class IncermenteWorker implements Runnable {
	
	private CompteurPartage compteurPartage;

	public CompteurPartage getCompteurPartage() { return compteurPartage; }
	public void setCompteurPartage(CompteurPartage compteurPartage) { this.compteurPartage = compteurPartage; }

	
	public IncermenteWorker(CompteurPartage compteurPartage) {
		this.compteurPartage = compteurPartage;
	}
	
	@Override
	public void run() {
		
		for (long l = 0; l < 100000000; l++) {
			if (l % 1000000 == 0) 
				System.out.println(Thread.currentThread().getName() + " -> " + l);
			
			//long compteur = this.compteurPartage.getCompteur() + 1;
			//this.compteurPartage.setCompteur(compteur);
			this.compteurPartage.incrementCompteurOptimise();
			
		}
		System.out.println(Thread.currentThread().getName() + " fini!");
	}

}
