package com.edugroupe.jpaheritageSingleTableForm.beans;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
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
@Entity 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "classtype", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "1")
public class Personne {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
															private String nom;
															private String prenom;
	

}
