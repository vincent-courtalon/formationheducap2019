package com.edugroupe.basicmappingForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.edugroupe.basicmappingForm.beans.Client;



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
		Client c1 = new Client(0, "Potter", "harry", 18, "harry@dumbledorefan.com");
		em.persist(c1);
		System.out.println(c1);
		Client c2 = new Client(0, "Granger", "hermione", 19, "hermione@gmail.com");
		em.persist(c2);
		Client c3 = new Client(0, "Rubeus", "hagrid", 39, "hagrid@gmail.com");
		em.persist(c3);
		
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
		// le  langage de requetage des entite s'apelle
		// EJBQL, JPQL, (specifique hibernate HQL)
		TypedQuery<Client> q1 = em.createQuery("select c from Client as c", Client.class);
		// execution de la "requete"
		List<Client> clients = q1.getResultList();
		for (Client c : clients) {
			System.out.println(c);
		}
		System.out.println("------------------------");
		
		/*
		 * ici, on retrouvera la même instance d'objet client que celle correspondant
		 * a l'id 2 dan liste requettée au dessus
		 * 
		 * cela abouti a une regle absolue en hibernate JPA
		 * dans un entityManager
		 * il n'ya jamais qu'une seule instance par ligne de table
		 * L'entity manager track/suit toutes les entiteés qu'il requette ou sauvegarde
		 *  l'entité est managé par l'entityManager
		 */
		Client c1 = em.find(Client.class, 2);
		System.out.println(c1);
		System.out.println("avant set");
		c1.setAge(22);
		c1.setAge(24);
		System.out.println("après set");
		List<Client> clients2 = q1.getResultList();
		for (Client c : clients2) {
			System.out.println(c);
		}
		System.out.println("avant commit");
		//----------------------------------------------------
		tx.commit();
		em.close();
		// l'entité n'est plus suivi par un entity manager
		// on dit que l'entité est dans un état "détaché"
		c1.setEmail("bob@eponge.com");
	}
	
	public static void test3(EntityManagerFactory emf)
	{
		// on recupere un entityManager
		EntityManager em = emf.createEntityManager();
		// et une transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//----------------------------------------------------
		
		// en jpql, on peut, comme en sql, parametre les requette
		// la syntaxe est :nom_parametre, le nom_parametre étant à votre choix
		// il suffit ensuite d'utiliser setParameter en leui redonnant le nom du parametre
		// pour attacher la valeur voulue
		TypedQuery<Client> q1 = em.createQuery(
				"select c from Client as c where c.age > :age_min", Client.class);
		q1.setParameter("age_min", 20);
		List<Client> clients = q1.getResultList();
		for (Client c: clients) {
			System.out.println(c);
		}
		
		// pas la peine d'utiliser une typedquery si on ne retourne pas une entité
		// mais certains attributs uniquement
		// cela nous renvoie alors de stableau d'objet, chaque objet correspondant a un attribut
		// renvoyé
		Query q2 = em.createQuery("select c.nom,c.prenom from Client as c where c.age < :age_max");
		q2.setParameter("age_max", 26);
		List<Object[]> data = q2.getResultList();
		for(Object[] ligne : data) {
			System.out.println(Arrays.toString(ligne));
		}
		
		Client c1 = em.find(Client.class, 1);
		// effacement dans la base
		em.remove(c1);
		
		//----------------------------------------------------
		tx.commit();
		em.close();

	}

}
