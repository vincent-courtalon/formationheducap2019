package validationtextform;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MaFenetre extends JFrame {
	
	private JTextField champRegex;
	private JTextField champValidation;
	
	// une expression reguliere , c'est un motif de texte a reconnaitre
	// "toto" -> "toto"
	// "t.to" -> "toto", "tato", trto", "t to" ...
	// "to+" -> "to" , "too", "tooo" ...   (+ 1 a n fois ce qui précede immédiatement)
	// "to*" -> 0 à n fois -> "t", "to", "too"...
	// "to?" -> 0 à 1 fois -> "t" ou "to"
	// "to{2,4}" -> 2 a 4 fois -> "too", "tooo", "toooo"
	// "pa(ri)+" -> "pari", "pariri", "paririri" ...
	
	// "p[aeiou]" -> "pa" , "pe", ...
	// "p[a-z]" -> "pa", "pb", ... "pz"
	// [a-zA-Z0-9] -> "a" ok "i" ok "R"  ok "3" OK "_" -> KO
	// [.] --> "."
	// [^a-zA-Z] --> n'importe quelle caractere sauf de a à z
	// ^ -> debut du motif   $ -> fin du motif
	// ^to -> "toti" ok  "atoti"
	// (ti|to|tu)  -> "ti"  ou "to" ou "tu"
	
	
	// code postal simple => [0-9]{5}
	
	// telephonne => 0152366478 ok 
	//				 01 52 36 64 78 ok
	//				 01.52.36.64.78 ok
	//				 01 52.36.64 78 ok (giga bonus ko)
	//				 0a 52 36 64 78 ko
	//				 01..52  36...64.78 ko
	
	// [0-9]{2}([. ]?[0-9]{2}){4}
	// [0-9]{2}(([0-9]{2}){4}|( [0-9]{2}){4}|([.][0-9]{2}){4})
	
	
	// email  =>	vincent.courtalon@gmol.com ok
	//		  =>	toto@yahoo.fr ok
	//		  =>    bob.eponge.super@orange.fr ok
	//		  =>    bob@@eponge.com KO
	//		  =>    bob				KO
	//		  =>    bob..eponge@orange.fr KO
	
	// [a-zA-Z0-9]+([.][a-zA-Z0-9]+)*@[a-zA-Z0-9]+([.][a-zA-Z0-9]+)*([.][a-zA-Z0-9]{2,4})
	
	// superbonus -> valider une url
	
	
	
	
	private Pattern regex;
	
	
	public MaFenetre() {
		super("validator");
		
		setSize(600, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		setLayout(layout);
		
		champRegex = new JTextField();
		champRegex.setFont(champRegex.getFont().deriveFont(40F));
		add(champRegex);
		champValidation = new JTextField();
		champValidation.setFont(champValidation.getFont().deriveFont(40F));
		add(champValidation);
		
		champRegex.getDocument().addDocumentListener(new DocumentListener() {
			private void valider(DocumentEvent e) {
				try {
					regex = Pattern.compile(champRegex.getText());
					champRegex.setBackground(Color.green);
				}
				catch (PatternSyntaxException ex) {
					regex = null;
					champRegex.setBackground(Color.pink);
				}
				validerTexte(e);
			}
			@Override
			public void removeUpdate(DocumentEvent e) { valider(e);}
			@Override
			public void insertUpdate(DocumentEvent e) { valider(e);}
			@Override
			public void changedUpdate(DocumentEvent e) {valider(e);}
		});
		
		champRegex.setText("[0-9]+");
		
		champValidation.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) { validerTexte(e);}
			@Override
			public void insertUpdate(DocumentEvent e) { validerTexte(e);}
			@Override
			public void changedUpdate(DocumentEvent e) {validerTexte(e);}
		});
		
		champValidation.setText("75016");
	}
	
	private void validerTexte(DocumentEvent e) {
		if (regex == null) {
			champValidation.setBackground(Color.yellow);
		}
		else {
			if (regex.matcher(champValidation.getText()).matches()) {
				champValidation.setBackground(Color.green);
			}
			else {
				champValidation.setBackground(Color.pink);
			}
		}
	}

}
