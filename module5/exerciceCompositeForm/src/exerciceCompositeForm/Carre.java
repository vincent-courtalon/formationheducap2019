package exerciceCompositeForm;

public class Carre extends Figure {
	private double cote;
	
	public double getCote() { return cote; }
	public void setCote(double cote) { this.cote = cote; }

	public Carre(double x, double y, double cote) {
		super(x, y);
		this.cote = cote;
	}
	
	@Override
	public void scale(double facteur) {
		setCote(getCote() * facteur); 
	}

	@Override
	public double getSurface() { // TODO Auto-generated method stub
		return getCote() * getCote();
	}
	
	@Override
	public String toString() {
		return "Carre [cote=" + cote + ", x=" + getX() + ", y=" + getY() + "]";
	}
	
	

}
