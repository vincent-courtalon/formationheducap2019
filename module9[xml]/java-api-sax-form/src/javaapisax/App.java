package javaapisax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class App {

	public static void main(String[] args) {
		
		try {
			// lecteur basique de xml SAX
			XMLReader reader = XMLReaderFactory.createXMLReader();
			// indique au lecteur de prévenir notre contentHandler de ce qu'il va
			// rencontrer dans le xml
			MyContentHandler mc = new MyContentHandler();
			reader.setContentHandler(mc);
			
			// lecture du fichier
			reader.parse(new InputSource(new FileInputStream("nutrition.xml")));
			
			System.out.println("serving total = " + mc.totalServing);
			
			System.out.println("---------------------");
			OutputStream sortie = new FileOutputStream(new File("sortie.xml"));
			XMLStreamWriter writer = 
					XMLOutputFactory.newInstance()
									.createXMLStreamWriter(sortie, "utf-8");
			
			writer.writeStartDocument("1.0");
				writer.writeStartElement("doc");
					writer.writeStartElement("titre");
						writer.writeCharacters("le xml, c'est le futur du passe");
					writer.writeEndElement();
					writer.writeStartElement("description");
						writer.writeAttribute("auteur", "vincent");
						writer.writeCharacters("le xml, c'est trop puissant, mais verbeux");
					writer.writeEndElement();
				writer.writeEndElement();
			writer.writeEndDocument();
			writer.close();
			
			
			
			
		} catch (SAXException e) {e.printStackTrace();} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();} 
		catch (XMLStreamException e) {e.printStackTrace();}
		catch (FactoryConfigurationError e) {e.printStackTrace();}
		

	}

}
