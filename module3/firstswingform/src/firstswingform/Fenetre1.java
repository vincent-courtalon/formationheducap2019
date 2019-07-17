package firstswingform;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Fenetre1 extends JFrame {
	
	public Fenetre1() {
		super("ma super fenetre");
		
		// taille de la fenetre 
		setSize(400, 300);
		// centrer la fenetre dans l'ecran
		setLocationRelativeTo(null);
		// demander a terminer le programme quand on ferme la fenetre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// flowLayout
		setLayout(new FlowLayout());
		
		JButton bouton1 = new JButton("cliquez moi!");
		// ajoute un bouton dans la fenetre
		add(bouton1);
		
		
		bouton1.addActionListener(e -> System.out.println("je suis cliqué!"));
		bouton1.addActionListener(e -> JOptionPane.showMessageDialog(null, "bonjour!"));
		
		for (int i = 1 ; i <= 9; i++) {
			add(new JButton("bouton " + i));
		}
	}

}
