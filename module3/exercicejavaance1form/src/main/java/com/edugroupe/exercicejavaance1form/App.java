package com.edugroupe.exercicejavaance1form;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.edugroupe.exercicejavaance1form.dao.ProduitCsvDAO;
import com.edugroupe.exercicejavaance1form.dao.ProduitDAO;
import com.edugroupe.exercicejavaance1form.metier.Produit;



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
					"jdbc:mysql://localhost:3306/base_java2",
					"root",
					"");
			System.out.println("nous somme connecté à la base!");
			
			// instanciation du dao des produits
			// cette objet permettra de requetter les acteurs dans la base
			ProduitDAO produitDAO = new ProduitDAO(base);
			ProduitCsvDAO produitCsvDAO = new ProduitCsvDAO(';');
			
			Scanner reader = new Scanner(System.in);
			
			while(true) {
				System.out.println("que souhaitez vous faire ?");
				System.out.println("1) lister les produits");
				System.out.println("2) editer un produit");
				System.out.println("3) creer un produit");
				System.out.println("4) effacer un produit");
				System.out.println("5) filter les produits par prix");
				System.out.println("6) exporter dans un csv");
				System.out.println("7) charger depuis un csv");
				System.out.println("8) quitter");
				
				int choix = Integer.parseInt(reader.nextLine());
				switch(choix) {
					case 1:
						listerProduit(produitDAO);
						break;
					case 2:
						editerProduit(produitDAO);
						break;
					case 3:
						insererProduit(produitDAO);
						break;
					case 4:
						effacerProduit(produitDAO);
						break;
					case 5:
						filterProduit(produitDAO);
						break;
					case 6:
						sauverProduit(produitDAO, produitCsvDAO);
						break;
					case 7:
						chargerProduit(produitCsvDAO);
						break;
				}
				// si choix a 5 , on sort du while
				if (choix == 8) {
					System.out.println("au revoir!");
					break;
				}
			}

			
			base.close();
		} catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}

    }	
    private static void chargerProduit(ProduitCsvDAO produitCsvDAO) {
       	System.out.println("nom du fichier ? ");
    	Scanner reader = new Scanner(System.in);
    	String filename = reader.nextLine();
    	try {
			produitCsvDAO.loadFromCsv(filename).stream().forEach(p -> System.out.println(p));
		} catch (FileNotFoundException e) {e.printStackTrace();}
    }
    
    private static void sauverProduit(ProduitDAO produitDAO, ProduitCsvDAO produitCsvDAO) {
    	System.out.println("nom du fichier ? ");
    	Scanner reader = new Scanner(System.in);
    	String filename = reader.nextLine();
    	try {
			produitCsvDAO.saveToCsv(produitDAO.findAll(), filename);
		} catch (FileNotFoundException e) {e.printStackTrace();}
    }
    
    private static void filterProduit(ProduitDAO produitDAO) {
    	Scanner reader = new Scanner(System.in);
    	System.out.println("prix minimum ? ");
    	double prixMin = Double.parseDouble(reader.nextLine());
    	System.out.println("prix maximum ? ");
    	double prixMax = Double.parseDouble(reader.nextLine());
    	
    	produitDAO.searchByPrice(prixMin, prixMax).stream().forEach(p -> System.out.println(p));
    }
    
    private static void listerProduit(ProduitDAO produitDAO) {
    	produitDAO.findAll().stream().forEach(p -> System.out.println(p));
    }
    
    private static void editerProduit(ProduitDAO produitDAO) {
    	listerProduit(produitDAO);
    	System.out.println("quel produit editer (id) ? ");
    	Scanner reader = new Scanner(System.in);
    	int id = Integer.parseInt(reader.nextLine());
    	
    	// on récupere l'acteur a editer
    	Produit p = produitDAO.findOne(id);
    	if ( p == null) {
    		System.out.println("produit inconnu");
    		return;
    	}
    	System.out.println("nouveau nom (vide pour ne pas changer '"+ p.getNom() + "') ?");
    	String saisie = reader.nextLine();
    	if (saisie.length() > 0)
    		p.setNom(saisie);
    	System.out.println("nouveau prix (vide pour ne pas changer '"+ p.getPrix() + "') ?");
    	saisie = reader.nextLine();
    	if (saisie.length() > 0)
    		p.setPrix(Double.parseDouble(saisie));
    	System.out.println("nouvel poids (vide pour ne pas changer '"+p.getPoids() + "') ?");
    	saisie = reader.nextLine();
    	if (saisie.length() > 0)
    		p.setPoids(Double.parseDouble(saisie));
    	produitDAO.save(p);
    }
    private static void insererProduit(ProduitDAO produitDAO) {
    	Scanner reader = new Scanner(System.in);

    	Produit p = new Produit();
    	
    	System.out.println("nouveau nom ? ");
    	String saisie = reader.nextLine();
    	p.setNom(saisie);
    	System.out.println("nouveau prix ? ");
    	saisie = reader.nextLine();
    	p.setPrix(Double.parseDouble(saisie));
    	System.out.println("nouveau poids ? ");
    	saisie = reader.nextLine();
    	p.setPoids(Double.parseDouble(saisie));
   		
    	System.out.println("produit sauvé: " +  produitDAO.save(p));
    }
    
    private static void effacerProduit(ProduitDAO produitDAO) {
    	Scanner reader = new Scanner(System.in);
    	listerProduit(produitDAO);
    	System.out.println("id du produit a effacer");
    	int id = Integer.parseInt(reader.nextLine());
    	produitDAO.delete(id);
    }
}
