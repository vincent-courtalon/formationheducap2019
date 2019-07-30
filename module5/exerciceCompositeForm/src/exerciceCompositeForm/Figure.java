package exerciceCompositeForm;

public abstract class Figure {
	
	private double x;
	private double y;
	
	public double getX() { return x; }
	public void setX(double x) { this.x = x; }
	public double getY() { return y; }
	public void setY(double y) { this.y = y; }
	
	public void move(double deplacement_x, double deplacement_y  ) {
		this.x += deplacement_x;
		this.y += deplacement_y;
	}
	
	public abstract void scale(double facteur);
	public abstract double getSurface();
	
	public Figure(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
