package webjdbcdaoform.metier;

public class Film {
	private int id;
	private String titre;
	private int longueur;
	private int annee;
	private String genre;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getTitre() { return titre; }
	public void setTitre(String titre) { this.titre = titre; }
	public int getLongueur() { return longueur; }
	public void setLongueur(int longueur) { this.longueur = longueur; }
	public int getAnnee() { return annee; }
	public void setAnnee(int annee) { this.annee = annee; }
	public String getGenre() { return genre; }
	public void setGenre(String genre) { this.genre = genre; }

	public Film() {}
	public Film(int id, String titre, int longueur, int annee, String genre) {
		this.id = id;
		this.titre = titre;
		this.longueur = longueur;
		this.annee = annee;
		this.genre = genre;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", longueur=" + longueur + ", annee=" + annee + ", genre="
				+ genre + "]";
	}

}
