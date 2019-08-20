package com.edugroupe.springjdbcForm.spring;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.edugroupe.springjdbcForm.beans.IProduitDAO;
import com.edugroupe.springjdbcForm.beans.ProduitDAO;
import com.edugroupe.springjdbcForm.metier.Produit;


public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
        Scanner input = new Scanner(System.in);
        
        
        input.nextLine();
        System.out.println("--------------------------------------");
        IProduitDAO produitDAO = ctx.getBean("produitDAO", IProduitDAO.class);
        
        List<Produit> produits = produitDAO.findAll();
        produits.stream().forEach(p -> System.out.println(p));
        
        input.nextLine();
        System.out.println("--------------------------------------");
        produits = produitDAO.findAll();
        produits.stream().forEach(p -> System.out.println(p));
        
        System.out.println("--------------------------------------");

        Produit p2 = produitDAO.findById(2);
        System.out.println("produit no2 -> " + p2);
        Produit p3 = produitDAO.findById(3);
        System.out.println("produit no3 -> " + p3);
        p2 = produitDAO.findById(2);
        System.out.println("produit no2 -> " + p2);
        
      /*  System.out.println("nom nouveau produit? ");
        String nom = input.nextLine();
        System.out.println("prix nouveau produit? ");
        double prix = Double.parseDouble(input.nextLine());
        System.out.println("poids nouveau produit? ");
        double poids = Double.parseDouble(input.nextLine());

        produitDAO.save(new Produit(0, nom, prix, poids));
      */
		System.out.println("done...");
	}





}
