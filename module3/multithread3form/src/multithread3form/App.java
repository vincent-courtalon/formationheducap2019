package multithread3form;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		
	//	Runnable r1;
	//	r1.

	//	Callable<Long> c1;
	//	c1.
		
	/*	TravailAttente ta = new TravailAttente(40);
		
		// Un executorService va se charger a votre place d'instancier et gerer les Thread
		// on fournis au service les runnable a executer, le le esxecutorService se charge de
		// presque tout le reste
		
		// pool de 4 thread pret a executer nos runnable
		 ExecutorService executeur = Executors.newFixedThreadPool(8);
		 
		 FibonacciRunnable f1 = new FibonacciRunnable(ta);
		 FibonacciRunnable f2 = new FibonacciRunnable(ta);
		 FibonacciRunnable f3 = new FibonacciRunnable(ta);
		 FibonacciRunnable f4 = new FibonacciRunnable(ta);
		// FibonacciRunnable f5 = new FibonacciRunnable(ta);
		// FibonacciRunnable f6 = new FibonacciRunnable(ta);
		// FibonacciRunnable f7 = new FibonacciRunnable(ta);
		// FibonacciRunnable f8 = new FibonacciRunnable(ta);
		 
		 Scanner reader = new Scanner(System.in);
		 System.out.println("appuyer sur entree pour demarrer");
		 reader.nextLine();
		
		 long debut = System.currentTimeMillis();
		 
		 // ---------------------------------------------------------------
		 System.out.println("je soumet mes taches a l'executeur");
		 executeur.execute(f1);
		 executeur.execute(f2);
		 executeur.execute(f3);
		 executeur.execute(f4);
	//	 executeur.execute(f5);
	//	 executeur.execute(f6);
	//	 executeur.execute(f7);
	//	 executeur.execute(f8);
		 
		 System.out.println("j'attend la fin de toutes mes taches");
		 
		 // arret "propre" de l'executeur
		 // il terminera les taches en cours, et n'en accepte plus de nouvelle
		 executeur.shutdown();
		 
		 // j'attend 2 minutes la fin...
		 try {
			executeur.awaitTermination(2, TimeUnit.MINUTES);
			System.out.println("fini executeur!");
		} catch (InterruptedException e) {e.printStackTrace();}
		//---------------------------------------------------------------- 
		long fin = System.currentTimeMillis();
		System.out.println("temps ecoulé: " + (fin - debut) + " millisecondes");
		 
		*/
		//ExecutorService executeur = Executors.newFixedThreadPool(8);
		/*
		ExecutorService executeur = Executors.newFixedThreadPool(4);
		ArrayList<FibonacciCallable> workers = new ArrayList<>();
		Random rd = new Random();
		for (int i = 0; i < 40; i++) {
			workers.add(new FibonacciCallable((int)(38 + rd.nextInt(5)), "worker no " + i));
		}
		ArrayList<Future<String>> resultats = new ArrayList<>();
		
		for (FibonacciCallable w : workers) {
			resultats.add(executeur.submit(w));
		}
		
		System.out.println("on arrette l'executeur");
		executeur.shutdown();
		
		try {
			// j'attend 4 seconde
			Thread.sleep(4000);
			System.out.println("future alors que pas terminé");
			for (Future<String> result : resultats) {
				if (result.isDone())
					System.out.println("fini -> " + result.get());
				else
					System.out.println("pas fini");
			}
			
			executeur.awaitTermination(2, TimeUnit.MINUTES);
			System.out.println("fini executeur!");
			System.out.println(" les resultats! ");
			
			for (Future<String> result : resultats) {
				System.out.println(result.get());
			}
		} catch (InterruptedException e) {e.printStackTrace();}
		catch (ExecutionException e) {e.printStackTrace();}
	*/	
		
		// lister le contenu d'un repertoire
		File f = new File("C:\\Program Files");
		if (f.exists() && f.isDirectory()) {
			String[] names = f.list();
			for (String name : names)
				System.out.println(name);
		}
		
		
	}

}
