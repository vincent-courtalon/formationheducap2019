package com.edugroupe.jpaheritageSingleTableForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.edugroupe.jpaheritageSingleTableForm.beans.Client;
import com.edugroupe.jpaheritageSingleTableForm.beans.Employe;
import com.edugroupe.jpaheritageSingleTableForm.beans.Personne;


public class JpaTest {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("testHibernate");
        Scanner input = new Scanner(System.in);

        input.nextLine();
        System.out.println("--------------------------------------");
		test1(emf);

        input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);

        input.nextLine();
		System.out.println("--------------------------------------");		

	    emf.close();
		System.out.println("done...");
	}




	public static void test1(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		em.persist(new Personne(0, "eponge", "bob"));
		em.persist(new Personne(0, "etoile", "patrick"));
		em.persist(new Personne(0, "poulpe", "carlo"));
		em.persist(new Personne(0, "ecureil", "sandy"));
		em.persist(new Personne(0, "crabs", "capitaine"));
		
		em.persist(new Employe(0, "Potter", "harry", "entretien", 1500.0));
		em.persist(new Employe(0, "Granger", "hermione", "accueil", 1700.0));
		em.persist(new Employe(0, "Weysley", "ron", "marketing", 1650.0));
		em.persist(new Employe(0, "Dumbledore", "albus", "ceo", 2650.0));
		em.persist(new Employe(0, "Rogue", "severus", "vente", 1850.0));
		
		em.persist(new Client(0, "Swcharzenegger", "arnold", "arnie@governator.com", 15000));
		em.persist(new Client(0, "Stalonne","sylvester", "rambo@survival.com", 12000));
		em.persist(new Client(0, "Foster", "jodie", "jodie@iwanttobelieve.com", 11000));
		em.persist(new Client(0, "Reeves", "keanu", "neo@matrix.com", 18000));
		em.persist(new Client(0, "Dujardin", "jean", "oss117@france.fr", 7000));
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}


	public static void test2(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		List<Personne> peoples = em.createQuery("from Personne", Personne.class).getResultList();
		peoples.stream().forEach(p -> System.out.println(p));
	
		System.out.println("--------------------------------------");
		
		List<Employe> employes = em.createQuery("from Employe", Employe.class).getResultList();
		employes.stream().forEach(e -> System.out.println(e));
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
