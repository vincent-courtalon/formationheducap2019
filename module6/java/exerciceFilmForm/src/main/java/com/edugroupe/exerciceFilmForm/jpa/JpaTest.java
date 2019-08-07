package com.edugroupe.exerciceFilmForm.jpa;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.edugroupe.exerciceFilmForm.beans.Film;
import com.edugroupe.exerciceFilmForm.beans.Producteur;


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
	
		Producteur p1 = new Producteur(0, "steven Spielberg", LocalDate.of(1946, 12, 18));
		Producteur p2 = new Producteur(0, "luc Besson", LocalDate.of(1959, 3, 18));
		Producteur p3 = new Producteur(0, "james Cameron", LocalDate.of(1967, 3, 29));
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		
		Film f1 = new Film(0, "jurassic park", LocalDate.of(1993, 6, 30), 125);
		f1.setProducteur(p1);
		Film f2 = new Film(0, "les dents de la mer", LocalDate.of(1975, 6, 30), 100);
		f2.setProducteur(p1);
		Film f3 = new Film(0, "ET", LocalDate.of(1982, 6, 30), 113);
		f3.setProducteur(p1);
		Film f4 = new Film(0, "le cinquieme element", LocalDate.of(1997, 5, 7), 126);
		f4.setProducteur(p2);
		Film f5 = new Film(0, "nikita", LocalDate.of(1990, 6, 30), 145);
		f5.setProducteur(p2);
		Film f6 = new Film(0, "Leon", LocalDate.of(1994, 6, 30), 110);
		f6.setProducteur(p2);
		Film f7 = new Film(0, "titanic", LocalDate.of(1997, 5, 7), 150);
		f7.setProducteur(p3);
		Film f8 = new Film(0, "terminator 2", LocalDate.of(1991, 5, 7), 108);
		f8.setProducteur(p3);
		Film f9 = new Film(0, "avatar", LocalDate.of(2009, 5, 7), 160);
		f9.setProducteur(p3);
		Film f10 = new Film(0, "star wars", LocalDate.of(1977, 5, 7), 121);
		
		em.persist(f1);
		em.persist(f2);
		em.persist(f3);
		em.persist(f4);
		em.persist(f5);
		em.persist(f6);
		em.persist(f7);
		em.persist(f8);
		em.persist(f9);
		em.persist(f10);
		
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
		
		List<Film> films = em.createQuery("from Film", Film.class).getResultList();
		films.stream().forEach(f -> System.out.println(f));
		
		System.out.println("---------------------------------");
		List<Producteur> producteurs = em.createQuery("select p from Producteur as p",
										Producteur.class).getResultList();
		producteurs.stream().forEach(p -> System.out.println(p));
		
		System.out.println("---------------------------------");
		TypedQuery<Film> q1 = em.createQuery("select f from Film as f where f.duree > :dureemin",
											Film.class);
		q1.setParameter("dureemin", 120);
		films = q1.getResultList();
		films.stream().forEach(f -> System.out.println(f));
		System.out.println("---------------------------------");
		
		TypedQuery<Film> q2 = em.createQuery(
				"select f from Film as f where f.producteur.dateNaissance > :dateMin",
				Film.class);
		q2.setParameter("dateMin", LocalDate.of(1951, 1, 1));
		films = q2.getResultList();
		films.stream().forEach(f -> System.out.println(f));
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
