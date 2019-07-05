package exercice4Form;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import exercice4Form.metier.Ticket;
import exercice4Form.metier.TicketLogiciel;
import exercice4Form.metier.TicketMateriel;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<Ticket> tickets = new ArrayList<>();
		tickets.add(new TicketMateriel(1, "serveur en feu",
				"combustion spontanée de la carte mere",
				9,
				"123456789",
				"edf"));
		tickets.add(new TicketLogiciel(2,
				"facturation arrondie à 0",
				"on perd plein d'argent",
				7,
				"comptayolo",
				"3.3"));
		tickets.add(new TicketMateriel(3, "bourrage imprimante",
				"l'imprimante imprime plus",
				4,
				"23458431",
				"le Monde"));
		tickets.add(new TicketLogiciel(4,
				"les mails sont répétés",
				"chaque mail est renvoyé 50 fois",
				6,
				"email_yolo",
				"2.2"));
		
		for (Ticket t : tickets)
			System.out.println(t);
		
		File f = new File("tickets.csv");
		PrintWriter pw = new PrintWriter(f);
		
		for (Ticket t : tickets)
			pw.println(t.saveToCsvLine());
		
		pw.close();
		
		System.out.println("sauvegardé");
		// chargement
		ArrayList<Ticket> loaded = new ArrayList<>();
		Scanner reader = new Scanner(f);
		
		while (reader.hasNext()) {
			loaded.add(Ticket.loadFromCsv(reader.nextLine()));
		}
		reader.close();
		
		for (Ticket t : loaded)
			System.out.println(t);
		

	}

}
