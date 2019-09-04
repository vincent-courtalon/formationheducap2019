package com.edugroupe.springuploadrepbaseform.metier;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
public class Picture {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
								private long id;
								private String titre;
								private LocalDateTime dateAdded;
								// meta information image
								private String fileName;
	@Column(length = 100)		private String contentType;
								private int width;
								private int height;
								// meta information de stockage
	@JsonIgnore
	@Column(length=60)			private String storageid; // clef de stockage
	@JsonIgnore
	@Column(length=60)			private String fileHash; // somme de controle du fichier
	@JsonIgnore
	@Column(length=60)			private String thumbStorageId; // clef de stockage de la miniature
				
	@JsonIgnore
	@ManyToMany					private Set<Tag> tags;
	
	//@OneToOne(mappedBy =  private Tag tag;
	
	public Picture(long id, String titre, LocalDateTime dateAdded, String fileName, String contentType, int width,
			int height, String storageid, String fileHash, String thumbStorageId) {
		super();
		this.id = id;
		this.titre = titre;
		this.dateAdded = dateAdded;
		this.fileName = fileName;
		this.contentType = contentType;
		this.width = width;
		this.height = height;
		this.storageid = storageid;
		this.fileHash = fileHash;
		this.thumbStorageId = thumbStorageId;
	}
	
	@PrePersist
	public void beforeInsert() {
		this.dateAdded = LocalDateTime.now();
	}
	
}
