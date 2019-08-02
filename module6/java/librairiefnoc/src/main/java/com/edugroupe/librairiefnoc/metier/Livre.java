package com.edugroupe.librairiefnoc.metier;

public class Livre {
	private int id;
	private String titre;
	private String isbn;
	private int nbPages;
	private String auteur;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getTitre() { return titre; }
	public void setTitre(String titre) { this.titre = titre; }
	public String getIsbn() { return isbn; }
	public void setIsbn(String isbn) { this.isbn = isbn; }
	public int getNbPages() { return nbPages; }
	public void setNbPages(int nbPages) { this.nbPages = nbPages; }
	public String getAuteur() { return auteur; }
	public void setAuteur(String auteur) { this.auteur = auteur; }
	
	public Livre() {}
	public Livre(int id, String titre, String isbn, int nbPages, String auteur) {
		super();
		this.id = id;
		this.titre = titre;
		this.isbn = isbn;
		this.nbPages = nbPages;
		this.auteur = auteur;
	}
	
	@Override
	public String toString() {
		return "Livre [id=" + id + ", titre=" + titre + ", isbn=" + isbn + ", nbPages=" + nbPages + ", auteur=" + auteur
				+ "]";
	}
	
	
}
