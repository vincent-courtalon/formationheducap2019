package exercicejava6Aform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class App {

	public static void main(String[] args) {

		List<Film> films = new ArrayList<Film>();
		films.add(new Film(1, "cendrillon", 125, 6));
		films.add(new Film(2, "aladdin", 135, 7));
		films.add(new Film(3, "cendrillon, la vengeance", 112, 8));
		films.add(new Film(4, "aladdin contre attaque", 102, 6));
		films.add(new Film(5, "cendrillon contre aladdin", 108, 7));
		
		
		for(Film f : films)
			System.out.println(f);
		
		System.out.println("--------------------");
		
		Collections.sort(films);
		for(Film f : films)
			System.out.println(f);
		
		

	}

}
