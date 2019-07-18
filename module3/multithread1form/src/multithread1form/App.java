package multithread1form;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		// quand votre programme démarre
		// il y a en fait un Thread en execution
		// généralement appelé "Main"
		
		// pour exploiter la programmation multiThread
		// il nous faut des instances de Thread (une par tache parallele)
		// plusieurs strategies possibles
		//  -> instancier un Thread basique et lui passer en parametre
		// une objet qui implement l'interface Runnable
		//	-> creer une classe héritant de la classe Thread, et overrider
		// certaines de méthodes
		//  -> utiliser les services d'un Executor
		
	/*	
		TacheFibonacci t1 = new TacheFibonacci("worker fib A");
		Scanner reader = new Scanner(System.in);
		System.out.println("appuyer sur entree pour démarrer");
		reader.nextLine();
		long debut = System.currentTimeMillis();
		// premiere boucle fibonacci
		t1.run();
		// deuxieme boucle fibonacci
		t1.run();
		long fin = System.currentTimeMillis();
		
		System.out.println("fini! en " + (fin - debut)  + " millisecondes");
		
		TacheFibonacci t2 = new TacheFibonacci("worker fib B");
		TacheFibonacci t3 = new TacheFibonacci("worker fib C");
		System.out.println("appuyer sur entree pour démarrer");
		reader.nextLine();
		debut = System.currentTimeMillis();
		t1.start();
		t2.start(); // ca execute le contenu de run dans un veritable thread en parallele
		t3.start();	// idem
		// 3 thread: Main, t2 et t3
		System.out.println("thread lancés");
		
		try {
			t1.join();
			t2.join(); // attendre la fin du thread t2
			t3.join(); // attendre la fin du thread t3
		} catch (InterruptedException e) {e.printStackTrace();}
		
		fin = System.currentTimeMillis();
		System.out.println("fini! en " + (fin - debut)  + " millisecondes");
		
		*/
		
		// le runnable contien le code a executer par un thread
		Runnable r1 = new RunnableFibonacci();
		Runnable r2 = new RunnableFibonacci();
		
		// les thread chargé d'executer les runnable
		Thread t1 = new Thread(r1 /*() -> System.out.println("hoho")*/);
		Thread t2 = new Thread(r2);
		
		Scanner reader = new Scanner(System.in);
		System.out.println("appuyer sur entree pour démarrer");
		reader.nextLine();
		long debut = System.currentTimeMillis();
		t1.start();
		t2.start();
		
		try {
			System.out.println("j'attend la fin de t1 et t2");
			t1.join();
			t2.join();
			System.out.println("fini!");
		} catch (InterruptedException e) {e.printStackTrace();}
		
		long fin = System.currentTimeMillis();
		System.out.println("fini! en " + (fin - debut)  + " millisecondes");
		
		
 
	}

}
