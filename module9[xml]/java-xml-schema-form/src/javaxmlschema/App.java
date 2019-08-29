package javaxmlschema;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class App {

	public static void main(String[] args) {
		
		// on instancie le generateur de schema
		SchemaFactory sfact = SchemaFactory
								.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		// on charge le fichier xml a valider
		Source fichier = new StreamSource(new File("repertoire.xml"));

		try {
			// chargement du schema
			Schema schema = sfact.newSchema(new File("repertoire.xsd"));
			// recuperer un validateur
			Validator validator = schema.newValidator();
			
			//valider nortre fichier XML
			validator.validate(fichier);
			System.out.println("le fichier est valide !");
			
		} catch (SAXException e) {
			System.out.println("le fichier xml est invalide " + e);
		}
		catch (IOException e) {	e.printStackTrace();}
		
	}

}
