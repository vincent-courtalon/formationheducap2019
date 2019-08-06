package com.edugroupe.associationsMappingForm.jpa;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.edugroupe.associationsMappingForm.beans.Categorie;
import com.edugroupe.associationsMappingForm.beans.Commande;
import com.edugroupe.associationsMappingForm.beans.Produit;


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

       /* input.nextLine();
		System.out.println("--------------------------------------");
		test2(emf);
*/
/*	    input.nextLine();
	    System.out.println("--------------------------------------");
		test3(emf);
*/
	    input.nextLine();
	    System.out.println("--------------------------------------");
		test4(emf);
		
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
		Categorie c1 = new Categorie(0, "boucherie");
		Categorie c2 = new Categorie(0, "epicerie");
		Categorie c3 = new Categorie(0, "céreales");
		
		Commande cmd1 = new Commande(0, "bob eponge", LocalDate.of(2017,10,10));
		Commande cmd2 = new Commande(0, "patrick etoile", LocalDate.of(2018,11,11));
		em.persist(cmd1);
		em.persist(cmd2);
		
		//em.persist(c1);
		//em.persist(c2);
		//em.persist(c3);
		
		Produit p1 = new Produit(0, "steak de lama des andes", 29.99, 10);
		p1.setCategorie(c1);
		//c1.getProduits().add(p1); // cosmétique, n'impacte pas la base
		Produit p2 = new Produit(0, "tofu tout fou", 8.99, 4);
		p2.setCategorie(c2);
		//c2.getProduits().add(p2);
		Produit p3 = new Produit(0, "escalope d'autruche", 24.99, 25);
		p3.setCategorie(c1);
		//c1.getProduits().add(p3);
		Produit p4 = new Produit(0, "miel des carpathes", 5.99, 7);
		p4.setCategorie(c2);
		//c2.getProduits().add(p4);
		Produit p5 = new Produit(0, "biere aux algues", 4.99, 12);

		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.persist(p4);
		em.persist(p5);

		
		cmd1.getProduits().add(p1);
		cmd1.getProduits().add(p2);
		cmd1.getProduits().add(p4);
		
		cmd2.getProduits().add(p1);
		cmd2.getProduits().add(p5);
		cmd2.getProduits().add(p3);
		
		
		
//		em.persist(c1);
//		em.persist(c2);
//		em.persist(c3);

		//
		c1.getProduits().stream().forEach(p -> System.out.println(p));
		
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
		
		/*Produit p1 = em.find(Produit.class, 1);
		System.out.println(p1);
		System.out.println(p1.getCategorie());
		*/
		
		/*List<Produit> produits = em.createQuery("from Produit", Produit.class).getResultList();
		for (Produit p : produits) {
			System.out.println(p.getNom() + " - " 
					+ ((p.getCategorie() != null) ?
							 p.getCategorie().getLibelle() : " pas de categorie"));
		}
		*/
		Categorie c1 = em.find(Categorie.class, 1);
		System.out.println(c1);
		System.out.println("-------------------------------------");
		System.out.println(Arrays.toString(c1.getProduits().toArray()));
		
		//em.remove(c1);
		
		//----------------------------------------------------
		tx.commit();
		em.close();
		System.out.println(Arrays.toString(c1.getProduits().toArray()));
	}

	
	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		// jpa traduit automatiquement la navigation dans les attributs des entités
		// en jointure quand cela est nécéssaire
		Query q1 = em.createQuery(
				"select p.nom,p.categorie.libelle from Produit as p where p.prix > :prixmin");
		q1.setParameter("prixmin", 8.0);
		List<Object[]> result = q1.getResultList();
		for (Object[] ligne : result) {
			System.out.println(Arrays.toString(ligne));
		}

		
		
		//----------------------------------------------------
		tx.commit();
		em.close();

	}

	public static void test4(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		/*System.out.println("creation nouvelle commande");
		Commande cmd = new Commande(0, "carlo poulpe", LocalDate.now());
		em.persist(cmd);
		
		Produit p1 =em.find(Produit.class, 1);
		p1.addCommande(cmd);		
		//p1.getCommandes().add(cmd);
		Produit p3 =em.find(Produit.class, 3);
		p3.addCommande(cmd);
		//p3.getCommandes().add(cmd);
		
		//cmd.getProduits().add(em.find(Produit.class, 1));
		//cmd.getProduits().add(em.find(Produit.class, 3));
		
		Produit p2 = em.find(Produit.class, 2);
		//p2.cleanCommandeBeforeRemove();
		em.remove(p2);
		//Commande cmd2 = em.find(Commande.class, 1);
		//em.remove(cmd2);
		*/
		
		TypedQuery<Commande> q1 = em.createQuery(
				"select distinct(cmd) from Commande as cmd left join fetch cmd.produits as p",
				Commande.class);
		List<Commande> commandes = q1.getResultList();
		System.out.println("----------------------------------");
		for (Commande cmd : commandes) {
			System.out.println(cmd + " nb produits = " + cmd.getProduits().size());
		}
		
		//----------------------------------------------------
		tx.commit();
		em.close();

	}
}
