package com.edugroupe.springuploadrepbaseform.metier;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString(exclude= {"users"})
@Entity
public class Role {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
	@Column(length = 100, nullable = false, unique = true) 	private String roleName;
	@ManyToMany(mappedBy = "roles") @JsonIgnore 			private Set<User> users;
	
	
	public Role(int id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	
}
