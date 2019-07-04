package exercice3Form.metier;

public class Materiel {
	private String noSerie;
	private int age;
	private String localisation;
	private Article article;
	
	public String getNoSerie() {return noSerie;}
	public void setNoSerie(String noSerie) {this.noSerie = noSerie;}
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	public String getLocalisation() {return localisation;}
	public void setLocalisation(String localisation) {this.localisation = localisation;}
	public Article getArticle() {return article;}
	public void setArticle(Article article) {this.article = article;}
	
	public Materiel() {
		this("", 0, "", null);
	}
	public Materiel(String noSerie, int age, String localisation, Article article) {
		setNoSerie(noSerie);
		setAge(age);
		setLocalisation(localisation);
		setArticle(article);
	}
	
	@Override
	public String toString() {
		return "Materiel [noSerie=" + noSerie + ", age=" + age + ", localisation=" + localisation + ", article="
				+ article + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Materiel))
			return false;
		Materiel other = (Materiel)obj;
		if (!this.noSerie.equals(other.noSerie))
			return false;
		if (!this.article.equals(other.article))
			return false;
		return true;
	}
	
	
	
	
	
	

}
