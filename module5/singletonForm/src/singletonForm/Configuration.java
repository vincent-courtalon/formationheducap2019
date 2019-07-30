package singletonForm;

public class Configuration {

	private String version;
	private String langue;
	
	public String getVersion() { return version; }
	public void setVersion(String version) { this.version = version; }
	public String getLangue() { return langue; }
	public void setLangue(String langue) { this.langue = langue; }
	
	// cette attribut statique va mémoriser l'unique instance de Configuration
	private static Configuration theInstance = null;

	// constructeur inaccessible depuis l'exterieur, car privé
	private Configuration() {
		this.version = "1.0";
		this.langue = "français";
	}
	
	// on ne peut obtenir l'unique instance que via cette méthode
	// elle sera "instanciée" la première fois qu'on la demande
	public static Configuration getTheInstance() {
		if (theInstance == null) {
			theInstance = new Configuration();
		}
		return theInstance;
	}
	
	@Override
	public String toString() {
		return "Configuration [version=" + version + ", langue=" + langue + "]";
	}
	
	
	
}
