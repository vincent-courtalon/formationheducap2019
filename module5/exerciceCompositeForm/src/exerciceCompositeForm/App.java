package exerciceCompositeForm;

public class App {

	public static void main(String[] args) {

		//Figure f1 = new Figure(5, 6);

		GroupeFigure gfracine = new GroupeFigure(3, 4);
		gfracine.addFigure(new Carre(-2, 10, 3));
		gfracine.addFigure(new Cercle(-3, -4, 5));
		
		GroupeFigure gf1 = new GroupeFigure(5,  7);
		gf1.addFigure(new Cercle(10, 15, 2));
		gf1.addFigure(new Cercle(10, 25, 2.5));
		
		gfracine.addFigure(gf1);
		
		GroupeFigure gf2 = new GroupeFigure(8,  9);
		gf2.addFigure(new Carre(8, 10, 3.5));
		gf2.addFigure(new Carre(20, 30, 15.5));
		
		gf1.addFigure(gf2);
		
		
		System.out.println(gfracine);
		System.out.println("surface = " + gfracine.getSurface());
		gfracine.move(5.5,  5.5);
		System.out.println(gfracine);
		gf1.scale(2.0);
		System.out.println(gfracine);
		
		
		
		
	}

}
