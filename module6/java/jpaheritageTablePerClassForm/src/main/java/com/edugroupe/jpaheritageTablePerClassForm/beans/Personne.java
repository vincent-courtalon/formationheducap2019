package com.edugroupe.jpaheritageTablePerClassForm.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne {
	
	@TableGenerator(name = "mes_sequences",
					table = "mes_sequences",
					pkColumnName = "nom_sequence",
					valueColumnName = "valeur_sequence",
					pkColumnValue = "personne",
					/*initialValue = 1,*/
					allocationSize = 50
					)
	@Id @GeneratedValue(strategy = GenerationType.TABLE, generator = "mes_sequences" )
															private int id;
															private String nom;
															private String prenom;
	

}
