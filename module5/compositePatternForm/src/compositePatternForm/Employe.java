package compositePatternForm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Employe {
	private String nom;
	private String poste;
	private double salaire;
	private List<Employe> subordonnes;
	
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public String getPoste() { return poste; }
	public void setPoste(String poste) { this.poste = poste; }
	public double getSalaire() { return salaire; }
	public void setSalaire(double salaire) { this.salaire = salaire; }
	public List<Employe> getSubordonnes() { return subordonnes; }
	public void setSubordonnes(List<Employe> subordonnes) { this.subordonnes = subordonnes; }
	
	public Employe(String nom, String poste, double salaire) {
		this.nom = nom;
		this.poste = poste;
		this.salaire = salaire;
		this.subordonnes = new ArrayList<Employe>();
	}
	
	//----------------------------------------------------
	// gestion des subordonnés
	public void addEmploye(Employe emp) {
		this.subordonnes.add(emp);
	}
	
	public void removeEmploye(Employe emp) {
		this.subordonnes.remove(emp);
	}
	//----------------------------------------------------	
	
	@Override
	public String toString() {
		return "Employe [nom=" + nom + ", poste=" + poste + ", salaire=" + salaire + "]";
	}
	
	public List<Employe> listerSubordonnes() {
		List<Employe> emps = new ArrayList<>(this.getSubordonnes());
		for (Employe e : getSubordonnes()) {
			emps.addAll(e.listerSubordonnes());
		}
		return emps;
	}
	
	public List<Employe> listerSubordonnesPoste(String poste) {
		List<Employe> emps = new ArrayList<>(
				this.getSubordonnes().stream()
									 .filter(e -> e.poste.equals(poste))
								     .collect(Collectors.toList()));
		// je demande le même filtrage a mes subordonnés, et ainsi de suite récursivement
		for (Employe e : getSubordonnes()) {
			emps.addAll(e.listerSubordonnesPoste(poste));
		}
		return emps;
	}
	
	
}
