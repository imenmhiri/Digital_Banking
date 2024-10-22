package com.fss.Digital.Banking.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Client implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private String nom;
	@Column
	private String prenom;
	@Column
	private String email;
	@OneToMany(mappedBy = "client")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private List<CompteBancaire> compteBancaire;
	// les 3 derniers attrributs sont utile pour l'authentification
	@Column
	private String username;
	@Column
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@Column
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> role;

}
