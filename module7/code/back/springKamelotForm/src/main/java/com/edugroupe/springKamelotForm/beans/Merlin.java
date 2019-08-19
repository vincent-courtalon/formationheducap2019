package com.edugroupe.springKamelotForm.beans;

import java.util.List;
import java.util.Random;

public class Merlin {
	private Random rd;
	
	private List<String> actions;
	private List<String> sujets;
 	private List<String> qualificatifs;
 	
	public Random getRd() { return rd; }
	public void setRd(Random rd) { this.rd = rd; }
	public List<String> getActions() { return actions; }
	public void setActions(List<String> actions) { this.actions = actions; }
	public List<String> getSujets() { return sujets; }
	public void setSujets(List<String> sujets) { this.sujets = sujets; }
	public List<String> getQualificatifs() { return qualificatifs; }
	public void setQualificatifs(List<String> qualificatifs) { this.qualificatifs = qualificatifs; }
 	
 	public Quete genererQuete() {
 		String description = 	this.actions.get(rd.nextInt(actions.size())) + " " +
 								this.sujets.get(rd.nextInt(sujets.size())) + " " +
 								this.qualificatifs.get(rd.nextInt(qualificatifs.size()));
 		
 		if (rd.nextBoolean()) {
 			QueteBasique qb = new QueteBasique(rd);
 			qb.setDifficulte(rd.nextDouble() * 7.0);
 			qb.setDescription(description);
 			return qb;
 		}
 		else {
			QueteEpique qe = new QueteEpique(rd);
 			qe.setDifficulte(rd.nextDouble() * 10.0);
 			qe.setDescription(description);
 			return qe;
 		}
 	}
	
	
}
