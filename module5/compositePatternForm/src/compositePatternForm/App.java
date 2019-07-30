package compositePatternForm;

import java.util.Random;

public class App {

	public static void main(String[] args) {
		
		Employe ceo = new Employe("elon musk", "direction", 100000);
		Employe managerVente = new Employe("bob eponge", "direction", 40000);
		Employe managerProduction = new Employe("patrick etoile", "direction", 39000);
		Employe managerRecherche = new Employe("carlo poulpe", "direction", 41000);
		ceo.addEmploye(managerVente);
		ceo.addEmploye(managerProduction);
		ceo.addEmploye(managerRecherche);
		
		String[] noms = {"vader", "skywalker", "thanos", "bonaparte", "stark", "voldemort", "house"};
		String[] prenoms = {"tony", "luke", "tyrion", "dark", "doc", "napoleon", "john", "marie"};
		String[] postes = {"ingenieur", "comptabilite", "commercial", "entretien"};
		
		Random rd = new Random();
		Employe[] submanager = new Employe[10];
		for (int i = 0; i < submanager.length; i++) {
			Employe mng = new Employe(
					prenoms[rd.nextInt(prenoms.length)] + " " + noms[rd.nextInt(noms.length)],
					postes[rd.nextInt(postes.length)],
					rd.nextDouble() * 30000 + 10000);
			submanager[i] = mng;
			// j'affecte ces manager a un des 3 directeurs
			switch(rd.nextInt(3)) {
				case 0: managerVente.addEmploye(mng); break;
				case 1: managerProduction.addEmploye(mng); break;
				case 2: managerRecherche.addEmploye(mng); break;
			}
		}
		// employe de base (non manager)
		for (int i = 0; i < 50 ; i++) {
			Employe ep = new Employe("jonh" + i + " doe", 
									postes[rd.nextInt(postes.length)],
									rd.nextDouble() * 20000 + 5000);
			// j'affecte l'employe a un des sous-manager
			submanager[rd.nextInt(submanager.length)].addEmploye(ep);
		}
		
		//--------------------------------------------------
		// test pattern composite
		//--------------------------------------------------
		
		System.out.println("lister tous les employes sous le ceo");
		ceo.listerSubordonnes().stream().forEach(e -> System.out.println(e));
		
		System.out.println("lister les ingenieurs dans le departement recherche");
		managerRecherche.listerSubordonnesPoste("ingenieur")
						.stream().forEach(e -> System.out.println(e));
		

	}

}
