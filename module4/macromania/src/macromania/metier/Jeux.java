package macromania.metier;

public class Jeux {
	private int id;
	private String titre;
	private String description;
	private String plateforme;
	private int annee;
	
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getTitre() { return titre; }
	public void setTitre(String titre) { this.titre = titre; }
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	public String getPlateforme() { return plateforme; }
	public void setPlateforme(String plateforme) { this.plateforme = plateforme; }
	public int getAnnee() { return annee; }
	public void setAnnee(int annee) { this.annee = annee; }
	
	public Jeux() {}
	public Jeux(int id, String titre, String description, String plateforme, int annee) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.plateforme = plateforme;
		this.annee = annee;
	}
	
	@Override
	public String toString() {
		return "Jeux [id=" + id + ", titre=" + titre + ", description=" + description + ", plateforme=" + plateforme
				+ ", annee=" + annee + "]";
	}
	
}
