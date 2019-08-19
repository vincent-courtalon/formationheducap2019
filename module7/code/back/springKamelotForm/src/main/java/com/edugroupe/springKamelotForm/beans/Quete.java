package com.edugroupe.springKamelotForm.beans;

public interface Quete {
	String getDescription();
	void setDescription(String description);
	// competence, plus il est compétent, plus il a de chance de realiser la quete
	boolean realiser(double competence);

}
