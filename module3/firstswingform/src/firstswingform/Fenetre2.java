package firstswingform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Fenetre2 extends JFrame implements ActionListener, DocumentListener {
	public final static String LOAD_COMMAND = "load";
	public final static String SAVE_COMMAND = "save";
	
	
	private JButton boutonLoad;
	private JButton boutonSave;
	private JTextField nomFichier;
	private JTextArea contenuFichier;
	
	private JMenuBar barreMenu;
	private JMenu 	 menuFichier;
	private JMenu 	 menuEdition;
	
	public Fenetre2() {
		super("ma giga fenetre");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// definit cinq zones dans la fenetre ou placer un composant
		// ouest, est, nord, sud, centre
		setLayout(new BorderLayout());
		
		// un panel horizontal au nord de ma fenetre
		JPanel panelNord = new JPanel();
		BoxLayout layoutNord = new BoxLayout(panelNord, BoxLayout.X_AXIS);
		panelNord.setLayout(layoutNord);
		
		add(panelNord, BorderLayout.NORTH);
		
		// je remplis mon panel nord
		boutonLoad = new JButton("chargement");
		panelNord.add(boutonLoad);
		boutonSave = new JButton("sauvegarde");
		panelNord.add(boutonSave);
		nomFichier = new JTextField();
		panelNord.add(nomFichier);
		// ma fenetre (via actionPerformed) ecoute au click de ces deux boutons
		boutonLoad.addActionListener(this);
		boutonLoad.setActionCommand(LOAD_COMMAND);
		boutonSave.addActionListener(this);
		boutonSave.setActionCommand(SAVE_COMMAND);
		
		// je veux mettre ma textArea au centre, avec barre de défilement
	
		contenuFichier = new JTextArea();
		add(new JScrollPane(contenuFichier), BorderLayout.CENTER);
		
		// ----------------------------------------------------------
		// creation des menus
		// ----------------------------------------------------------
		barreMenu = new JMenuBar();
		menuFichier = new JMenu("fichier");
		// entree chargement du menu fichier
		JMenuItem charger = new JMenuItem("chargement");
		charger.setActionCommand(LOAD_COMMAND);
		charger.addActionListener(this);
		menuFichier.add(charger);
		
		// entree sauvegarde du menu fichier
		JMenuItem sauver = new JMenuItem("sauvegarde");
		sauver.setActionCommand(SAVE_COMMAND);
		sauver.addActionListener(this);
		menuFichier.add(sauver);
		
		// ajout du menu fichier dans la barre de menu
		barreMenu.add(menuFichier);
		
		// activer la barre de menu pour notre fenetre
		setJMenuBar(barreMenu);
		
		
		// ecouter le changement de contenu du champ nomFichier
		nomFichier.getDocument().addDocumentListener(this);
		
		documentChanged(null);
		// vider le champ au début
		//nomFichier.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case LOAD_COMMAND:
				//JOptionPane.showMessageDialog(null, "load");
				chargerFichier();
				break;
			case SAVE_COMMAND:
				sauverFichier();
				break;
		}
		
	}
	
	private void sauverFichier() {
		String filename = nomFichier.getText();
		File f = new File(filename);
		try {
			PrintWriter pw = new PrintWriter(f);
			pw.print(contenuFichier.getText());
			pw.close();
			JOptionPane.showMessageDialog(null, "sauvegarde effectuée");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, 
					"impossible d'ecrire", 
					"erreur",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void chargerFichier() {
		// contenu textuer du textField
		String filename = nomFichier.getText();
		File f = new File(filename);
		if (f.exists() && f.canRead() && f.isFile()) {
			try {
				Scanner reader = new Scanner(f);
				StringBuilder sb = new StringBuilder();
				while (reader.hasNext()) {
					sb.append(reader.nextLine()).append("\n");
				}
				reader.close();
				contenuFichier.setText(sb.toString());
			} catch (FileNotFoundException e) {	e.printStackTrace();}
		}
		else {
			JOptionPane.showMessageDialog(null, 
										"fichier non lisible", 
										"erreur",
										JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

	private void documentChanged(DocumentEvent e) {
		String filename = nomFichier.getText();
		File f = new File(filename);
		if (f.exists() && f.isFile() && f.canRead()) {
			nomFichier.setBackground(Color.green);
			boutonLoad.setEnabled(true);
		}
		else
		{
			nomFichier.setBackground(Color.pink);
			boutonLoad.setEnabled(false);
		}
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {documentChanged(e);}
	@Override
	public void removeUpdate(DocumentEvent e) {documentChanged(e);}
	@Override
	public void changedUpdate(DocumentEvent e) {documentChanged(e);}
}
