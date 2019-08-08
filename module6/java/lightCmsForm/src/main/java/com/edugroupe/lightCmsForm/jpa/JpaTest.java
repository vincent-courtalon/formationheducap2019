package com.edugroupe.lightCmsForm.jpa;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.edugroupe.lightCmsForm.beans.Contenu;
import com.edugroupe.lightCmsForm.beans.DocumentPDF;
import com.edugroupe.lightCmsForm.beans.Galerie;
import com.edugroupe.lightCmsForm.beans.Image;
import com.edugroupe.lightCmsForm.beans.Tag;



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
		em.persist(new Tag(0, "cuisine"));
		em.persist(new Tag(0, "voyage"));
		em.persist(new Tag(0, "bricolage"));
		em.persist(new Tag(0, "informatique"));
		em.persist(new Tag(0, "sport"));
		
		Galerie g1 = new Galerie(0, "gallerie1", "lama mignons");
		Galerie g2 = new Galerie(0, "gallerie2", "java surpuissant");
		Galerie g3 = new Galerie(0, "gallerie3", "quidditch");
		
		em.persist(g1);
		em.persist(g2);
		em.persist(g3);
		
		// generer 100 contenu (1/3 de document, 2/3 d'images)
		Random rd = new Random();
		for (int i = 1; i <= 100; i++) {
			Contenu c = null;
			if (i % 3 == 0) {
				c = new DocumentPDF(0, "doc" + i, "titre" + i, "bob");
			}
			else {
				Image img = new Image(0, "img" + i, "img" + i +  ".jpg", "image/jpeg");
				// 1 chance sur 6 d'etre affecter à gallerie 1 ou 2 ou 3
				// 1/2 chance de n'estre dans aucune gallerie
				int tirage = rd.nextInt(6);
				if (tirage == 1)
					img.setGalerie(g1);
				if (tirage == 2)
					img.setGalerie(g2);
				if (tirage == 3)
					img.setGalerie(g3);
				c = img;
			}
			// pour chaque tag, une chance sur deux d'etiqueter ce contenu
			for (int j = 1; j <= 5; j++) {
				if (rd.nextBoolean()) {
					c.addTag(em.find(Tag.class, j));
				}
			}
			em.persist(c);
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
		
		// 1) lister toutes les images etiquetée avec un certain tag
		TypedQuery<Image> q1 = em.createQuery(
				"select i from Image as i join i.tags as t where t.id=:tid",
				Image.class);
		q1.setParameter("tid", 1);
		//q1.setFirstResult(10);  --> equivalent du limit
		//q1.setMaxResults(10);	
		q1.getResultList().stream().forEach(img -> System.out.println(img));
		System.out.println("------------------------------------");

		// 2) lister les galeries et le nombre d'images qu'elle contienne
		Query q2 = em.createQuery("select g, count(i) from Galerie as g "
									+ "join g.images as i group by g");
		((List<Object[]>)(q2.getResultList()))
						.stream()
						.forEach(ligne -> System.out.println(Arrays.toString(ligne)));
		System.out.println("------------------------------------");

		// 4) lister les tags et le nombre de contenu qu'ils étiquettent
		TypedQuery<Object[]> q3 = em.createQuery(
				"select t, count(c) from Tag as t join t.contenus as c group by t",
				Object[].class);
		q3.getResultList().stream().forEach(ligne -> System.out.println(Arrays.toString(ligne)));
		System.out.println("------------------------------------");
		
		// 3) lister les contenus etiquetés par 2 tag choisi en même temps
		TypedQuery<Image> q4 = em.createQuery(
				"select i from Image as i "
				+ "join i.tags as t1 "
				+ "join i.tags as t2 "
				+ "where t1.id=:tid1 AND t2.id=:tid2",
				Image.class);
		q4.setParameter("tid1", 1);
		q4.setParameter("tid2", 2);
		q4.getResultList().stream().forEach(img -> System.out.println(img));
		System.out.println("------------------------------------");

		// 5) lister les images etiquetée par un tag choisi et non etiqueté
		// par un autre tag
		TypedQuery<Image> q5 = em.createQuery(
				"select i from Image as i join i.tags as t "
				+ " where t.id  = :tid1 "
				+ " AND NOT EXISTS ( "
				+ " select i2 from Image as i2 "
				+ " join i2.tags as t2 "
				+ " where t2.id = :tid2 "
				+ " AND i2.id = i.id )", 
				Image.class);
		q5.setParameter("tid1", 1);
		q5.setParameter("tid2", 2);
		q5.getResultList().stream().forEach(img -> System.out.println(img));
		
		System.out.println("---------------------------------------------");
		// je recupere notre "query builder a la sauce jpa"
		CriteriaBuilder cbuilder = em.getCriteriaBuilder();
		
		CriteriaQuery<DocumentPDF> query = cbuilder.createQuery(DocumentPDF.class);
		Root<DocumentPDF> from = query.from(DocumentPDF.class);
	
		TypedQuery<DocumentPDF> q6 = em.createQuery(query.select(from));
		
		q6.getResultList().stream().forEach(d -> System.out.println(d));
		
		
		
	
		//----------------------------------------------------
		tx.commit();
		em.close();
	}

}
