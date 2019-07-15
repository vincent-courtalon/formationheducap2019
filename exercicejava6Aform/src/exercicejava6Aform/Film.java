package exercicejava6Aform;

public class Film  implements Comparable<Film>
{
	private int id;
	private String titre;
	private int longueur;
	private int score;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public int getLongueur() {return longueur;}
	public void setLongueur(int longueur) {this.longueur = longueur;}
	public int getScore() {return score;}
	public void setScore(int score) {this.score = score;}
	
	public Film(int id, String titre, int longueur, int score) {
		this.id = id;
		this.titre = titre;
		this.longueur = longueur;
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", titre=" + titre + ", longueur=" + longueur + ", score=" + score + "]";
	}
	
	@Override
	public int compareTo(Film autre) { 
		// je compare le score du  plus grand vers le plus petit
		int scoreComparison=  -Integer.compare(score, autre.score);
		// si le score est identique
		if (scoreComparison == 0)
			// je compare la longuer de la plus petite a la plus grande
			return Integer.compare(longueur, autre.longueur);
		else
			// sinon je renvoie la comparaison entre les scores
			return scoreComparison;
	}
	
	

}
