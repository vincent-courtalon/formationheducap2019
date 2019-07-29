package observerPatternForm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileAfficheur implements EntierObserver {
	private EntierSujet sujet;
	private File fichier;
	private PrintWriter pw;
	
	
	public FileAfficheur(EntierSujet sujet, File fichier) {
		this.sujet = sujet;
		this.fichier = fichier;
		try {
			this.pw = new PrintWriter(fichier);
			this.sujet.addObserver(this);
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}

	public void close() {
		this.pw.close();
	}


	@Override
	public void update(int entier) {
		this.pw.println(entier);
	}

}
