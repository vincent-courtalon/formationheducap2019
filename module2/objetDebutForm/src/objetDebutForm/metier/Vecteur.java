package objetDebutForm.metier;

// ma classe Vecteur herite de la classe Object
// c'est le cas par défaut, si on ne précise pas d'héritage
// en Java, la classe Object est à la racine de tous les heritages
public class Vecteur extends Object{
	// statique, lié à la classe en général et pas à une instance particulière
	
	public final static double UNITE_VALEUR_DEFAUT = 1.0; // final -> constante
	private static int nbVecteurCree = 0;
	
	// une méthode statique peut être appelée depuis la classe (sans instance)
	// mais elle n'a pas access aux attributs non statiques
	public static int getNbVecteurCree() {
		return nbVecteurCree;
	}
	
	private double x;
	private double y;
	private double z;
	
	public double getX() {return x;}
	public void setX(double x) {this.x = x;}
	public double getY() {return y;}
	public void setY(double y) {this.y = y;}
	public double getZ() {return z;}
	public void setZ(double z) {this.z = z;}
	
	public Vecteur() {
		this(UNITE_VALEUR_DEFAUT, UNITE_VALEUR_DEFAUT, UNITE_VALEUR_DEFAUT);
	}
	public Vecteur(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		nbVecteurCree++;
	}
	// constructeur par copie
	public Vecteur(Vecteur other) {
		this(other.x, other.y, other.z);
	}
	
	
	public void add(Vecteur other) {
		this.x += other.x;
		this.y += other.y;
		this.z += other.z;
	}

	public void sub(Vecteur other) {
		this.x -= other.x;
		this.y -= other.y;
		this.z -= other.z;
	}

	public void scale(double facteur) {
		this.x *= facteur;
		this.y *= facteur;
		this.z *= facteur;
	}
	
	@Override
	public String toString() {
		// [x,y,z] 
		return "["+ x + "," + y + "," + z + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		// si l'autre n'est pas un vecteur, pas egal
		if (!(obj instanceof Vecteur))
			return false;
		// converti mon parametre Object en Vecteur
		Vecteur other = (Vecteur)obj;
		return this.x == other.x && this.y == other.y && this.z == other.z;
		
	}
	
	

}
