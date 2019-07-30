package reflectionJavaForm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Fenetre extends JFrame implements ActionListener
{
	public static final String INSTANCIER_COMMAND = "new"; 
	public static final String REQUETTER_COMMAND = "requetter";
	
	private JTextField className;
	private JButton instancier;
	private JButton requetter;
	
	private JList<Object> myBeans;
	private DefaultListModel<Object> myBeansData;
	
	// connection a la base de donnée
	private Connection base;
	
	public Fenetre() {
		super("instanciator!");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel panelHaut = new JPanel();
		BoxLayout bl = new BoxLayout(panelHaut, BoxLayout.X_AXIS);
		panelHaut.setLayout(bl);
		
		className = new JTextField(70);
		panelHaut.add(className);
		
		
		instancier = new JButton("instancier");
		instancier.setActionCommand(INSTANCIER_COMMAND);
		panelHaut.add(instancier);
		
		add(panelHaut, BorderLayout.NORTH);
		
		myBeansData = new DefaultListModel<>();
		myBeans = new JList<>(myBeansData);
		add(new JScrollPane(myBeans), BorderLayout.CENTER);
		
		instancier.addActionListener(this);
		
		// sql/requetage
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			base = DriverManager.getConnection("jdbc:mysql://localhost:3306/base_java2", "root", "");
			
		}
		catch (ClassNotFoundException e) {e.printStackTrace();}
		catch (SQLException e) {e.printStackTrace();}
		
		requetter = new JButton("requetter produits");
		panelHaut.add(requetter);
		requetter.setActionCommand(REQUETTER_COMMAND);
		requetter.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case INSTANCIER_COMMAND:
				instanciate();
				break;
			case REQUETTER_COMMAND:
				requetter_action();
				break;
		}
		
	}
	
	public void requetter_action() {
		try {
			DynamicDAO<Produit> dao = new DynamicDAO<>(base, Produit.class);
			myBeansData.clear();
			List<Produit> produits = dao.findAll();
			for (Produit p : produits)
				myBeansData.addElement(p);
		} 
		catch (NoSuchMethodException e) {	e.printStackTrace();}
		catch (SecurityException e) {e.printStackTrace();}
		
	}
	
	
	public void instanciate() {
		try {
			Class clazz = Class.forName(className.getText());
			myBeansData.clear();
			myBeansData.addElement("nom classe = " + clazz.getSimpleName());
			myBeansData.addElement("nom package = " + clazz.getPackage().getName());
			
			// liste des setter de l'objet
			ArrayList<Method> setters = new ArrayList<>();
			
			for (Method m : clazz.getDeclaredMethods() ) {
				if (!Modifier.isPublic(m.getModifiers())) // si ce n'est pas publique, ca ne nous interesse pas
					continue;
				if (Modifier.isStatic(m.getModifiers())) // si static, pas intéréssant
					continue;
				// on ne veut que les methode qui on un parametre
				Class[] params = m.getParameterTypes();
				if (params.length != 1)
					continue;
				if (m.getName().startsWith("set")) {
					// c'est bon, c'est un setter valide
					setters.add(m);
				}
			}
			
			// j'instancie l'objet vide
			Object instance = clazz.newInstance();
			
			// j'affiche mes setters 	
			for (Method m : setters) {
				String saisie = JOptionPane.showInputDialog(null,
									"valeur pour " + m.getName()
									+ " de type " + m.getParameterTypes()[0].getSimpleName());
				if (m.getParameterTypes()[0].equals(String.class)) {
					// set d'une chaine de caractere
					m.invoke(instance, saisie);
				}
				if (m.getParameterTypes()[0].equals(double.class)) {
					// set d'une valeur double
					m.invoke(instance, Double.parseDouble(saisie));
				}
				if (m.getParameterTypes()[0].equals(int.class)) {
					// set d'un entier
					m.invoke(instance, Integer.parseInt(saisie));
				}
			}
			// j'ajoute mon bean instancier dans la liste
			myBeansData.addElement(instance);
			
		} catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();}
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {e.printStackTrace();}
		
	}
	
	
	
	
	
}
