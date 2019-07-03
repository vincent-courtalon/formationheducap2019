package exercice2AForm;

public class App {

	public static void main(String[] args) {
		findMinMax(2.5, -8, 10, 42, 21, -15, 12 , 0);

	}
	
	public static void findMinMax(double ... data) {
		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;
		// double min = data[0];
		
		for (double d : data) {
			if (d < min)
				min = d;
			if (d > max)
				max = d;
		}
		System.out.println("maximum " + max);
		System.out.println("minimum " + min);
	}

}
