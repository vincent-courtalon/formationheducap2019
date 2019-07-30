package exerciceCompositeForm;

import java.util.ArrayList;
import java.util.List;

public class GroupeFigure extends Figure {
	private List<Figure> figures;
	
	public List<Figure> getFigures() { return figures; }
	public void setFigures(List<Figure> figures) { this.figures = figures; }
	
	public GroupeFigure(double x, double y) {
		super(x, y);
		this.figures = new ArrayList<Figure>();
	}

	public void addFigure(Figure f) {
		this.figures.add(f);
	}
	
	@Override
	public void scale(double facteur) {
		this.figures.stream().forEach(f -> f.scale(facteur));
	}

	@Override
	public double getSurface() { 
		double total = 0.0;
		for (Figure f : figures) {
			total += f.getSurface();
		}
		return total;
	}
	
	@Override
	public void move(double deplacement_x, double deplacement_y) {
		super.move(deplacement_x, deplacement_y);
		this.figures.stream().forEach(f -> f.move(deplacement_x, deplacement_y));
	}
	
	@Override
	public String toString() {
		return "GroupeFigure [figures=" + figures + ", x=" + getX() + ", y=" + getY() + "]";
	}
	

}
