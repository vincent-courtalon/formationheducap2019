package exercice4Form.metier;

public class TicketMateriel extends Ticket {
	private String noMateriel;
	private String localisation;
	
	public String getNoMateriel() {return noMateriel;}
	public void setNoMateriel(String noMateriel) {this.noMateriel = noMateriel;}
	public String getLocalisation() {return localisation;}
	public void setLocalisation(String localisation) {this.localisation = localisation;}

	
	
	public TicketMateriel() {}
	public TicketMateriel(int id, String titre, String description, int urgence, String noMateriel,
			String localisation) {
		super(id, titre, description, urgence);
		setNoMateriel(noMateriel);
		setLocalisation(localisation);
	}
	public TicketMateriel(String[] champs) {
		super(champs);
		setNoMateriel(champs[4]);
		setLocalisation(champs[5]);
	}

	
	@Override
	public String toString() {
		return "TicketMateriel [noMateriel=" + noMateriel + ", localisation=" + localisation + "]" 
				+ super.toString();
	}
	
	@Override
	public String getTypeName() {
		return "tm";
	}

	@Override
	public String description() {
		return getTitre() + " sur materiel no " + getNoMateriel();
	}
	
	@Override
	public String saveToCsvLine() {
		StringBuilder sb =new StringBuilder();
		sb.append(super.saveToCsvLine())
		  .append(',')
		  .append(getNoMateriel())
		  .append(',')
		  .append(getLocalisation());
		return sb.toString();
	}
	
	

}
