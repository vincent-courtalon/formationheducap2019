package javaapidom;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class App {

	public static void main(String[] args) {

		// utilisation API DOM
		// permet de configurer le parsing de document xml
		// comme par exemple l'utilisation de schema de validation
		// ou encore de namespace
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// Le documentBuilder est l'objet concret qui permet
			// de : parser un fichier xml vers un objet Document (dom)
			// construire un document vide directement
			DocumentBuilder db = dbf.newDocumentBuilder();
			// lire un fichier xml et construire sa représentation objet
			Document doc1 = db.parse(new File("book.xml"));

			// on a acces à l'api DOM

			// récuperer toutes les balises "title" du document
			NodeList tags = doc1.getElementsByTagName("title");
			// qu'est ce qu'un node
			// c'est la classe de base de tout ce qu'on peu rencotre en xml
			// -> Element (balise)
			// -> Attribute (attribut)
			// -> Texte (texte)
			for (int i = 0; i < tags.getLength(); i++) {
				Element balise = (Element) tags.item(i);
				System.out.println("tag name = " + balise.getTagName());
				System.out.println("texte interieur = " + balise.getTextContent());
			}
			System.out.println("XPATH -------------------------------");
			
			XPathFactory xpf = XPathFactory.newInstance();
			XPath xp = xpf.newXPath();
			// requette XPath
			XPathExpression xpe = xp.compile("//catalog/book[price>10]/title/text()");
			//execution de la requette
			NodeList result = (NodeList)xpe.evaluate(doc1, XPathConstants.NODESET);
			// parcour des résultats
			for (int i = 0; i < result.getLength(); i++) {
				System.out.println(result.item(i).getNodeValue());
			}
			
			System.out.println("XPATH -------------------------------");
			System.out.println("XPATH2 -------------------------------");
			Document doc2 = db.parse(new File("nutrition.xml"));
			
			//XPathExpression xpe2 = xp.compile("//nutrition/food/name/text()");
			//XPathExpression xpe2 = xp.compile("//nutrition/food[cholesterol>20]/name/text()");
			XPathExpression xpe2 = xp.compile("//nutrition/food/calories[@fat<50]/../name/text()");
			result = (NodeList)xpe2.evaluate(doc2, XPathConstants.NODESET);
			for (int i = 0; i < result.getLength(); i++) {
				System.out.println(result.item(i).getNodeValue());
			}
			System.out.println("XPATH2 -------------------------------");
			System.out.println("XPATH3 -------------------------------");
			//XPathExpression xpe3 = 
			//	xp.compile("sum(//nutrition/food/calories[@fat<50]/../protein)");
			XPathExpression xpe3 = 
					xp.compile("sum(//nutrition/food/calories[@fat<50]/../protein) "
							+ " div count(//nutrition/food/calories[@fat<50])");

			double moyenne = (double)xpe3.evaluate(doc2, XPathConstants.NUMBER);
			System.out.println("moyenne proteines = " + moyenne);
			
			
			System.out.println("XPATH3 -------------------------------");
			System.out.println("CREATION XML -------------------------------");
			
			// document vide
			Document doc3 = db.newDocument();
			// balise racine du document
			Element racine = doc3.createElement("comptes");
			doc3.appendChild(racine);
			
			Random rd = new Random();
			for (int i = 0; i < 10; i++) {
				// une balise "compte"
				Element compte = doc3.createElement("compte");
				/// ajout d'un attribut "id"
				compte.setAttribute("id", "" + (i + 1));
				
				// creation de sous balises
				Element solde = doc3.createElement("solde");
				Text texte = doc3.createTextNode("" + (rd.nextDouble() * 1000.0));
				solde.appendChild(texte);
				compte.appendChild(solde);
				
				Element proprietaire = doc3.createElement("proprietaire");
				// equivalent a creer un textNode dans la balise
				proprietaire.setTextContent("bob" + i);
				compte.appendChild(proprietaire);
				
				racine.appendChild(compte);
			}
			// ecriture, on passe par des transformers
			TransformerFactory tfact = TransformerFactory.newInstance();
			// source
			DOMSource source = new DOMSource(doc3);
			// destination -> nouveau fichier xml
			StreamResult destination = new StreamResult(new File("comptes.xml"));
			// ecrivain de xml
			Transformer tf = tfact.newTransformer();
			// on veu de l'indentation
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			// ecriture
			tf.transform(source, destination);
			
			System.out.println("ecriture 2 -----------------------------------");
			XPathExpression xpe4 = xp.compile("//nutrition/food/calories[@fat<50]/..");
			result = (NodeList)xpe4.evaluate(doc2, XPathConstants.NODESET);
			
			Document doc4 = db.newDocument();
			racine = doc4.createElement("selectefoods");
			doc4.appendChild(racine);
			for (int i = 0; i < result.getLength(); i++) {
				// balise food provenant de nutrition.xml
				Element food = (Element)result.item(i);
				Element nourriture = doc4.createElement("nourriture");
				nourriture.appendChild(doc4.adoptNode(food.getElementsByTagName("name").item(0)));
				nourriture.appendChild(doc4.adoptNode(food.getElementsByTagName("cholesterol").item(0)));
				nourriture.appendChild(doc4.adoptNode(food.getElementsByTagName("sodium").item(0)));
				racine.appendChild(nourriture);
			}
			
			source = new DOMSource(doc4);
			destination = new StreamResult(new File("selectefood.xml"));
			tf.transform(source, destination);
			
			
			//explore(doc1.getChildNodes());

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void explore(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node n = nodes.item(i);
			switch (n.getNodeType()) {
				case Node.COMMENT_NODE:
					System.out.println("commentaire: " + ((Comment)n).getTextContent());
					break;
				case Node.TEXT_NODE:
					System.out.println("texte : " + n.getNodeValue());
					break;
				case Node.ELEMENT_NODE:
					Element el = (Element)n;
					System.out.println("balise: " + el.getTagName());
					// sur une balise ouvrante
					// je peux parcourir ses attributs
					NamedNodeMap attr = el.getAttributes();
					for (int j = 0; j < attr.getLength(); j++) {
						System.out.println("attribut: " + attr.item(j).getNodeName() 
								+ " - "  + attr.item(j).getNodeValue());					}
					//------------------------------------
					System.out.println(">>>>>>>>>>>>>>>>");
					explore(n.getChildNodes());
					System.out.println("<<<<<<<<<<<<<<<<");
					break;
				case Node.ATTRIBUTE_NODE:
					System.out.println("attribut: " + n.getNodeName());
					break;
			}
		}
	}

}
