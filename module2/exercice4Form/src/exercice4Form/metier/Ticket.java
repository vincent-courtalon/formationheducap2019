package exercice4Form.metier;

import java.util.Arrays;

public abstract class Ticket {
	private int id;
	private String titre;
	private String description;
	private int urgence;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public String getTitre() {return titre;}
	public void setTitre(String titre) {this.titre = titre;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getUrgence() {return urgence;}
	public void setUrgence(int urgence) {this.urgence = urgence;}
	
	
	public Ticket() {}
	public Ticket(int id, String titre, String description, int urgence) {
		setId(id);
		setTitre(titre);
		setDescription(description);
		setUrgence(urgence);
	}
	public Ticket(String[] champs) {
		this(Integer.parseInt(champs[0]),  // id
			 champs[1],						// titre
			 champs[2],						// description
			 Integer.parseInt(champs[3]));	// urgence
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", titre=" + titre + ", description=" + description + ", urgence=" + urgence + "]";
	}
	
	public abstract String getTypeName();
	public abstract String description();
	
	public String saveToCsvLine() {
		StringBuilder sb = new StringBuilder();
		sb.append(getTypeName())
		  .append(',')
		  .append(getId())
		  .append(',')
		  .append(getTitre())
		  .append(',')
		  .append(getDescription())
		  .append(',')
		  .append(urgence);
		return sb.toString();
	}
	
	// responsable du chargement d'un ticket
	public static Ticket loadFromCsv(String csvLine) {
		String[] champs = csvLine.split(",");
		switch(champs[0]) {
			case "tm":
	return new TicketMateriel(Arrays.copyOfRange(champs, 1, champs.length));
			case "tl":
	return new TicketLogiciel(Arrays.copyOfRange(champs, 1, champs.length));
		}
		return null;
	}
	
	

}
