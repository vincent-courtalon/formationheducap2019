package objetDebutForm.metier;

public class Livre {
	
	// etat ou attributs de ma classe Livre
	public int id;
	public String titre;
	public String auteur;
	private int nbPages;
	private double prix;
	
	// un getter de l'attribut nbPages
	public int getNbPages() {return nbPages;}
	// un setter de l'attribut nbPages
	public void setNbPages(int nbPages) {
		this.nbPages = (nbPages > 0)? nbPages : 0;
	}
	public double getPrix() {return prix;}
	public void setPrix(double prix) {this.prix = (prix > 0)? prix : 0.0;}
	
	
	// constructeur d'objets
	

	// constructeur par défaut de la classe livre
	// fournit automatiquement si on le définit pas
	public Livre() {
		this(0, "inconnu", "inconnu", 0, 0.0);
	}
	// autre constructeur
	public Livre(int id, String titre, String auteur, int nbPages, double prix) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		// même si le constructeur peut passer outre
		// il est recommandé de passer par les setter s'il y a des controles
		setNbPages(nbPages);
		setPrix(prix);
	}
	
	// operations ou méthodes de ma classe Livre
	
	public String description() {
		return "titre=" + titre + ", auteur=" + this.auteur;
	}
	
}
