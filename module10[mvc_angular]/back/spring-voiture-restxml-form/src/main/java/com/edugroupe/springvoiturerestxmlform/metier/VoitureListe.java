package com.edugroupe.springvoiturerestxmlform.metier;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "voitures")
public class VoitureListe {
	private List<Voiture> voitures;

	@XmlElement(name = "voiture")
	public List<Voiture> getVoitures() { return voitures; }
	public void setVoitures(List<Voiture> voitures) { this.voitures = voitures; }
	
	public VoitureListe() {}
	public VoitureListe(List<Voiture> voitures) {
		this.voitures = voitures;
	}
	
}
