package com.edugroupe.springuploadbaseform.metier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.edugroupe.springuploadbaseform.metier.projections.PictureWithoutFile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(exclude = {"content"})
@Entity
public class Picture {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
															private String titre;
	
	// meta information
															private String fileName;
	@Column(length = 100)									private String contentType;
															private int fileSize;
	
	// stockage du contenu
	@JsonIgnore @Lob @Column(length = 10*1024*1024)						private byte[] content;

	
	public Picture(int id, String titre, String fileName, String contentType, int fileSize) {
		super();
		this.id = id;
		this.titre = titre;
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
	}
	
	
	
}
