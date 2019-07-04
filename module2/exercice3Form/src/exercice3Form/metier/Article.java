package exercice3Form.metier;

public class Article {
	private String nom;
	private String description;
	private String famille;
	
	public String getNom() {return nom;}
	public void setNom(String nom) {this.nom = nom;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public String getFamille() {return famille;}
	public void setFamille(String famille) {this.famille = famille;}
	
	public Article() {
		this("", "", "");
	}
	public Article(String nom, String description, String famille) {
		setNom(nom);
		setDescription(description);
		setFamille(famille);
	}
	
	public Materiel createMateriel(String noSerie, int age, String localisation) {
		return new Materiel(noSerie, age, localisation, this);
	}
	
	@Override
	public String toString() {
		return "Article [nom=" + nom + ", description=" + description + ", famille=" + famille + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (famille == null) {
			if (other.famille != null)
				return false;
		} else if (!famille.equals(other.famille))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	
	
	

}
