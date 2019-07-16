package com.edugroupe.firstjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.edugroupe.firstjdbc.dao.ActeurDAO;
import com.edugroupe.firstjdbc.metier.Acteur;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		try {
			// connection a la base
			Class.forName("com.mysql.jdbc.Driver");
			Connection base = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/base_java1",
					"root",
					"");
			System.out.println("nous somme connecté à la base!");
			
			// instanciation du dao des acteurs
			// cette objet permettra de requetter les acteurs dans la base
			ActeurDAO acteurDAO = new ActeurDAO(base);
			
			Scanner reader = new Scanner(System.in);
			
			while(true) {
				System.out.println("que souhaitez vous faire ?");
				System.out.println("1) lister les acteurs");
				System.out.println("2) editer un acteur");
				System.out.println("3) creer un acteur");
				System.out.println("4) effacer un acteur");
				System.out.println("5) quitter");
				
				int choix = Integer.parseInt(reader.nextLine());
				switch(choix) {
					case 1:
						listerActeur(acteurDAO);
						break;
					case 2:
						editerActeur(acteurDAO);
						break;
					case 3:
						insererActeur(acteurDAO);
						break;
					case 4:
						effacerActeur(acteurDAO);
						break;
				}
				// si choix a 5 , on sort du while
				if (choix == 5) {
					System.out.println("au revoir!");
					break;
				}
			}
			/*
			// on demande la liste de tous les acteurs de la base
			// le dao nous le fournit sous la forme d'une liste d'objet metier Acteur
			List<Acteur> acteurs = acteurDAO.findAll();
			
			// on afficher les acteurs en question
			acteurs.stream().forEach(a -> System.out.println(a));
			
			// afficher un acteur
			System.out.println("------------------------");
			Acteur acteur = acteurDAO.findOne(3);
			System.out.println(acteur);
			*/
			
			base.close();
		} catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}

    }
    
    private static void listerActeur(ActeurDAO acteurDAO) {
    	acteurDAO.findAll().stream().forEach(a -> System.out.println(a));
    }
    
    private static void editerActeur(ActeurDAO acteurDAO) {
    	listerActeur(acteurDAO);
    	System.out.println("quel acteur editer (id) ? ");
    	Scanner reader = new Scanner(System.in);
    	int id = Integer.parseInt(reader.nextLine());
    	
    	// on récupere l'acteur a editer
    	Acteur a = acteurDAO.findOne(id);
    	if ( a == null) {
    		System.out.println("acteur inconnu");
    		return;
    	}
    	System.out.println("nouveau nom (vide pour ne pas changer '"+ a.getNom() + "') ?");
    	String saisie = reader.nextLine();
    	if (saisie.length() > 0)
    		a.setNom(saisie);
    	System.out.println("nouveau prenom (vide pour ne pas changer '"+ a.getPrenom() + "') ?");
    	saisie = reader.nextLine();
    	if (saisie.length() > 0)
    		a.setPrenom(saisie);
    	System.out.println("nouvel email (vide pour ne pas changer '"+ a.getEmail() + "') ?");
    	saisie = reader.nextLine();
    	if (saisie.length() > 0)
    		a.setEmail(saisie);
    	acteurDAO.save(a);
    }
    private static void insererActeur(ActeurDAO acteurDAO) {
    	Scanner reader = new Scanner(System.in);

    	Acteur a = new Acteur();
    	
    	System.out.println("nouveau nom ? ");
    	String saisie = reader.nextLine();
   		a.setNom(saisie);
    	System.out.println("nouveau prenom ? ");
    	saisie = reader.nextLine();
   		a.setPrenom(saisie);
    	System.out.println("nouvel email ? ");
    	saisie = reader.nextLine();
   		a.setEmail(saisie);
   		
    	acteurDAO.save(a);
    }
    
    private static void effacerActeur(ActeurDAO acteurDAO) {
    	Scanner reader = new Scanner(System.in);
    	listerActeur(acteurDAO);
    	System.out.println("id de l'acteur a effacer");
    	int id = Integer.parseInt(reader.nextLine());
    	acteurDAO.delete(id);
    }
}
