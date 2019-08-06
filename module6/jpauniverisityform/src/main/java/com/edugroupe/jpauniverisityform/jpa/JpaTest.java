package com.edugroupe.jpauniverisityform.jpa;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.edugroupe.jpauniverisityform.beans.Cours;
import com.edugroupe.jpauniverisityform.beans.Etudiant;
import com.edugroupe.jpauniverisityform.beans.Matiere;
import com.edugroupe.jpauniverisityform.beans.Professeur;


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
		
		String[] libelleMatieres = {"java", "opera", "parachutisme", "jedi"};
		for (String str: libelleMatieres)
			em.persist(new Matiere(0, str));
		
		em.persist(new Professeur(0, "torvald", "linus", 2500.0));
		em.persist(new Professeur(0, "bean", "mr", 1500.0));
		em.persist(new Professeur(0, "seagal", "steven", 1800.0));
		em.persist(new Professeur(0, "yoda", "maitre", 350.0));
		
		String[] qualificatif = {"avance", "debutant", "extreme", "pour les nuls"};
		String[] complement  = {"mystique", "de combat", "pratique", "baroque"};
		Random rd = new Random();
		for (int i = 1; i <= 10; i++) {
			Matiere m = em.find(Matiere.class, rd.nextInt(4) + 1);
			Professeur p = em.find(Professeur.class, rd.nextInt(4) + 1);
			Cours c = new Cours(0, m.getLibelle() + " " 
								+ qualificatif[rd.nextInt(4)] + " "
								+ complement[rd.nextInt(4)],
								rd.nextInt(20) + 15,
								LocalDate.of(2019,rd.nextInt(4) + 1,5),
								LocalDate.of(2019,rd.nextInt(4) + 6,5));
			c.setMatiere(m);
			c.setProfesseur(p);
			em.persist(c);
		}
		
		for (int i = 1; i <= 100; i++) {
			Etudiant e = new Etudiant(0, "bob" + i, "joe" + i, "bob" + i +"@gmail.com");
			for (int j = 1; j <= 10; j++) {
				if (rd.nextDouble() > 0.8) {
					em.find(Cours.class, j).addEtudiant(e);
				}
			}
			em.persist(e);
		}
		
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
		
		TypedQuery<Etudiant> q1 = em.createQuery(
				"select distinct(et) from Etudiant as et join et.courses as co "
				+ " where co.dateDebut > :debut order by et.id",
				Etudiant.class);
		q1.setParameter("debut", LocalDate.of(2019, 3, 1));
		List<Etudiant> etudiants = q1.getResultList();
		etudiants.stream().forEach(et -> System.out.println(et));
		
		System.out.println("--------------------------------------------");
		Query q2 = em.createQuery("select c.libelle, count(et.id) from Cours c "
								+ " join c.etudiants as et group by c");
		List<Object[]> data = q2.getResultList();
		data.stream().forEach(ligne -> System.out.println(Arrays.toString(ligne)));
		
		System.out.println("--------------------------------------------");
		Query q3 = em.createQuery("select c.libelle, count(et.id) * 100.0 / c.capacite from Cours c "
				+ " join c.etudiants as et group by c");
		List<Object[]> data3 = q3.getResultList();
		data3.stream().forEach(ligne -> System.out.println(Arrays.toString(ligne)));
		
		System.out.println("--------------------------------------------");
		Query q4 = em.createQuery("select co.matiere.libelle, count(distinct et.id) "
				+ " from Cours as co "
				+ " join co.etudiants as et "
				+ " where co.matiere.id = :mid"); 
		q4.setParameter("mid", 1);
		List<Object[]> data4 = q4.getResultList();
		data4.stream().forEach(ligne -> System.out.println(Arrays.toString(ligne)));
		
		System.out.println("--------------------------------------------");

		TypedQuery<Etudiant> q5 = em.createQuery(
				"select et from Etudiant as et WHERE NOT EXISTS ( "
				+ "select et2 from Etudiant as et2 "
				+ "join et2.courses as c "
				+ "where et2.id= et.id AND c.professeur.id = :pid)",
				Etudiant.class);
		q5.setParameter("pid", 3);
		List<Etudiant> fugitifs = q5.getResultList();
		fugitifs.stream().forEach(et -> System.out.println(et));
		
		
		
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
