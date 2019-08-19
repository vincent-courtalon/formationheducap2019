package com.edugroupe.springKamelotForm.beans;

public class Menestrel {
	
	public void chanterAvant(Chevalier chevalier) {
		System.out.println("Tralalalala, sire " + chevalier.getNom() 
						+  " part courageusement en quete!");
	}
	

	public void chanterApres(Chevalier chevalier) {
		System.out.println("Tralalalala, sire " + chevalier.getNom() 
						+  " revient joyeusement de quete!");
	}


}
