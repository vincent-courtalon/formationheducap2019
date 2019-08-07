package com.edugroupe.jpaheritagejointableForm.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity @Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
															private String nom;
															private String prenom;
	

}
