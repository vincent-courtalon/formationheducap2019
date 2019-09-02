package com.edugroupe.springuploadrepbaseform.metier;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class Tag {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
									private int id;
	@Column(length=100) 			private String libelle;
	@JsonIgnore
	@ManyToMany(mappedBy = "tags")	private Set<Picture> pictures;
	
	public Tag(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	

}
