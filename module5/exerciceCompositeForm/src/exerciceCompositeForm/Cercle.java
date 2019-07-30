package exerciceCompositeForm;

public class Cercle extends Figure {
	private double rayon;
	
	public double getRayon() { return rayon; }
	public void setRayon(double rayon) { this.rayon = rayon; }

	public Cercle(double x, double y, double rayon) {
		super(x, y);
		this.rayon = rayon;
	}
	
	@Override
	public void scale(double facteur) {
		setRayon(getRayon() * facteur); 
	}

	@Override
	public double getSurface() { 
		return getRayon() * getRayon() * Math.PI;
	}
	
	@Override
	public String toString() {
		return "Cercle [rayon=" + rayon + ", x=" + getX() + ", y=" + getY() + "]";
	}

	
}
