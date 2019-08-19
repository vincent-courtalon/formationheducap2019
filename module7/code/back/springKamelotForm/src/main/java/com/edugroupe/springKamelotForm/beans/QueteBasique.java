package com.edugroupe.springKamelotForm.beans;

import java.util.Random;

public class QueteBasique implements Quete {
	private String description;
	private double difficulte;
	private Random rd;
	
	
	public QueteBasique(Random rd) {
		super();
		this.rd = rd;
	}
	
	public double getDifficulte() { return difficulte; }
	public void setDifficulte(double difficulte) { this.difficulte = difficulte; }
	@Override
	public String getDescription() { return description;}
	@Override
	public void setDescription(String description) {this.description = description;}
	
	@Override
	public boolean realiser(double competence) {
		return (rd.nextDouble() * competence) > difficulte;
	}

	@Override
	public String toString() {
		return "QueteBasique [description=" + description + ", difficulte=" + difficulte + "]";
	}

	
}
