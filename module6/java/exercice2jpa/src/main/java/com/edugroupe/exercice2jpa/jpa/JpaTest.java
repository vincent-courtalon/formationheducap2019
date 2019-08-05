package com.edugroupe.exercice2jpa.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.edugroupe.exercice2jpa.beans.Produit;


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
		test3(emf);

		
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
		em.persist(new Produit(0, "steak de lama", 29.99, 0.75, 4));
		em.persist(new Produit(0, "tofu tout fou", 19.99, 1.0, 12));
		em.persist(new Produit(0, "quinoa des andes", 12.99, 0.5, 8));
		em.persist(new Produit(0, "chocolat 100% soja", 14.50, 0.25, 3));
		em.persist(new Produit(0, "biere aux algues", 7.99, 2.0, 11));
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
		List<Produit> produits = em.createQuery("FROM Produit", Produit.class).getResultList();
		for (Produit p : produits)
			System.out.println(p);
		
		Produit p = em.find(Produit.class, 3);
		p.setStock(p.getStock() + 3);
		
		//----------------------------------------------------
		tx.commit();
		em.close();
		// ce ne sera pas propager dans la base!
		p.setNom("quinoa des ameriques");
		saveproduit = p;
	}
	// me rappeler du produit entre 2 appels test2 et test3
	static Produit saveproduit;
	
	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		// em.persist(saveproduit);
		// sp est suivi par l'entity manager et a pris en compte
		// les modifications précédentes
		Produit sp = em.merge(saveproduit);
		System.out.println(sp);
		
		TypedQuery<Produit> q1 = em.createQuery(
				"select p from Produit as p where p.stock > :stock_min", Produit.class);
		q1.setParameter("stock_min", 5);
		q1.getResultList().stream().forEach(p -> System.out.println(p));
		
		TypedQuery<Produit> q2 = em.createQuery(
				"select p from Produit as p where p.stock < :stock_max", Produit.class);
		q2.setParameter("stock_max", 5);
		q2.getResultList().stream().forEach(p -> p.setPrix(p.getPrix() *1.1));
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}
}
