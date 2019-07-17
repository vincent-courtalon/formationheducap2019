package com.edugroupe.exercicejavaance1form.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.edugroupe.exercicejavaance1form.metier.Produit;

public class ProduitCsvDAO {
	
	private final char separateur;

	public ProduitCsvDAO(char separateur) {
		this.separateur = separateur;
	}
	
	public void saveToCsv(List<Produit> produits, String filename) throws FileNotFoundException {
		final PrintWriter pw = new PrintWriter(new File(filename));
		//pw = null;
		produits.stream().forEach(p -> pw.println(p.getId() + "" + separateur 
												+ p.getNom() + separateur
												+ p.getPrix() + separateur
												+ p.getPoids()));
		//pw = null;
		pw.close();
		
	}
	
	public List<Produit> loadFromCsv(String filename) throws FileNotFoundException {
		Scanner reader = new Scanner(new File(filename));
		ArrayList<Produit> produits = new ArrayList<>();
		while(reader.hasNext()) {
			String line = reader.nextLine();
			String[] champs = line.split(""+ separateur);
			produits.add(new Produit(Integer.parseInt(champs[0]),
									 champs[1],
									 Double.parseDouble(champs[2]),
									 Double.parseDouble(champs[3])));
		}
		reader.close();
		return produits;
	}
	
	

}
