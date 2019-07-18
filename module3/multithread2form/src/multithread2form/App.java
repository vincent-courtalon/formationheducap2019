package multithread2form;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		CompteurPartage cp1 = new CompteurPartage(0);
		
		IncermenteWorker w1 = new IncermenteWorker(cp1);
		IncermenteWorker w2 = new IncermenteWorker(cp1);

		Scanner reader = new Scanner(System.in);
		System.out.println("appuyer sur entree pour demarrer");
		reader.nextLine();
		
		long debut = System.currentTimeMillis();
		System.out.println(cp1);
		
		//w1.run();
		//w2.run();
		Thread t1 = new Thread(w1);
		Thread t2 = new Thread(w2);
		t1.start();
		t2.start();
		
		try {
			System.out.println("attente de t1 et t2");
			t1.join();
			t2.join();
		} catch (InterruptedException e) {e.printStackTrace();}
		
		
		System.out.println(cp1);
		long fin = System.currentTimeMillis();
		System.out.println("temps ecoulé: " + (fin - debut) + " millisecondes");
		
		
	}

}
