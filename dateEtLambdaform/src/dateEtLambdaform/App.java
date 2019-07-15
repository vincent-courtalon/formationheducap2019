package dateEtLambdaform;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class App {

	public static void main(String[] args) {
		
		//date originelle de java avant java 7
		Date d = new Date();
		
		System.out.println(d);
		// super bizarre
		Date d2 = new Date(2019, 3, 15);
		System.out.println(d2);
		d2.setYear(119);
		System.out.println(d2);

		// introduction des classe Calendar (GregorianCalendar...etc) dans java 7 (6?)
		// introduction dans java8 : LocalDate, etc
		// LocalDate -> cohérent, gestion des timezone, immutable
		LocalDate ld1 = LocalDate.of(2019, 4, 15);
		System.out.println(ld1);
		LocalDateTime ldt1 = LocalDateTime.of(2019, 4, 15, 13, 45);
		System.out.println(ldt1);
		LocalDate ld2 = ld1.plusMonths(1);
		System.out.println(ld1);
		System.out.println(ld2);
		
		Duration dur1 = Duration.of(15, ChronoUnit.MINUTES);
		System.out.println(dur1);
		
		ArrayList<Voiture> voitures = new ArrayList<>();
		voitures.add(new Voiture("peugot 206", 80, "rouge", LocalDate.of(1998, 10, 15)));
		voitures.add(new Voiture("tesla model s", 150, "bleue", LocalDate.of(2018, 10, 15)));
		voitures.add(new Voiture("alpha romeo guilletta", 120, "blanche", LocalDate.of(2005, 10, 15)));
		voitures.add(new Voiture("nissan leaf", 100, "rouge", LocalDate.of(2010, 10, 15)));
		voitures.add(new Voiture("delorean", 140, "grise", LocalDate.of(1981, 10, 15)));
		voitures.add(new Voiture("k2000", 300, "noire", LocalDate.of(1982, 10, 15)));
		voitures.add(new Voiture("renault fuego", 90, "rouge", LocalDate.of(1992, 10, 15)));

		/*
		// utilisation d'une classe anonyme interne implémentant l'interface Comparator
		voitures.sort(new Comparator<Voiture>() {

			@Override
			public int compare(Voiture o1, Voiture o2) {
				return Integer.compare(o1.getPuissanceChevaux(), o2.getPuissanceChevaux());
			}
		});
*/
		// les lambdas
		voitures.sort((o1, o2) -> Integer.compare(o1.getPuissanceChevaux(), o2.getPuissanceChevaux()));
		
		for (Voiture v : voitures) {
			System.out.println(v);
		}
		
		// un stream (flux) permet d'appliquer des traitements à tous
		// les elements d'un flux, comme une collection
		// et d'enchainer ces traitements
		// typiquement, les stream s'utilisent avec des lambdas
		
		List<String> noms = voitures.stream()
									.filter(v -> v.getDateConstruction().isAfter(LocalDate.of(1995, 1, 1)))
									.map(v -> v.getNom())
									.map(s -> s.toUpperCase())
									.collect(Collectors.toList());
				//.forEach(s -> System.out.println(s));
		
		noms.stream().forEach(n -> System.out.println(n));
		
	}

}
