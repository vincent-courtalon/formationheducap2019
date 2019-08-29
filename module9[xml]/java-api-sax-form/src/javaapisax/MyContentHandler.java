package javaapisax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class MyContentHandler implements ContentHandler {

	public double totalServing = 0;
	private boolean inServing = false;
	
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("debut du document");
	}
	@Override
	public void endDocument() throws SAXException {
		System.out.println("fin du document");
	}
	// balise ouvrante
	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		System.out.println("balise ouvrante --> " + localName);
		if (localName.equals("serving"))
			inServing = true;
	}
	// balise fermante
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("balise fermante --> " + localName);
		if (localName.equals("serving"))
			inServing = false;
	}
	// du texte dans notre document
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String texte = new String(ch, start, length);
		System.out.println("texte : '" + texte + "'");
		if (inServing) {
			double value = Double.parseDouble(texte);
			totalServing += value;
		}
	}
	
	@Override
	public void setDocumentLocator(Locator locator) {}
	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {}
	@Override
	public void endPrefixMapping(String prefix) throws SAXException {}
	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
	@Override
	public void processingInstruction(String target, String data) throws SAXException {}
	@Override
	public void skippedEntity(String name) throws SAXException {}

}
