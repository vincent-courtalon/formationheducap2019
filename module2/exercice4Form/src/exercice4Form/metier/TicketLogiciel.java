package exercice4Form.metier;

public class TicketLogiciel extends Ticket {
	private String nomLogiciel;
	private String version;
	
	public String getNomLogiciel() {return nomLogiciel;}
	public void setNomLogiciel(String nomLogiciel) {this.nomLogiciel = nomLogiciel;}
	public String getVersion() {return version;}
	public void setVersion(String version) {this.version = version;}

	
	public TicketLogiciel() {}
	public TicketLogiciel(int id, String titre, String description, int urgence, String nomLogiciel, String version) {
		super(id, titre, description, urgence);
		setNomLogiciel(nomLogiciel);
		setVersion(version);
	}
	public TicketLogiciel(String[] champs) {
		super(champs);
		setNomLogiciel(champs[4]);
		setVersion(champs[5]);
	}

	
	@Override
	public String toString() {
		return "TicketLogiciel [nomLogiciel=" + nomLogiciel + ", version=" + version + "]"
				+ super.toString();
	}
	
	@Override
	public String getTypeName() {
		return "tl";
	}

	@Override
	public String description() {
		return getTitre() + " pour logiciel " + getNomLogiciel();
	}
	
	@Override
	public String saveToCsvLine() {
		StringBuilder sb =new StringBuilder();
		sb.append(super.saveToCsvLine())
		  .append(',')
		  .append(getNomLogiciel())
		  .append(',')
		  .append(getVersion());
		return sb.toString();
	}

}
