package com.fss.Digital.Banking.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fss.Digital.Banking.enums.CompteStatus;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 10)
/*
 * Puisqu’il y on a un seul attribut pour chacune des classes dérivées on peut
 * se contenter de la stratégie single table
 */
public class CompteBancaire implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private Date datedecreation;
	@Column
	private double solde;
	@Enumerated(EnumType.STRING)
	/* Si on ne prend pas EnumType string dans le table on va voir compte status 0 
	 *  ACTIVE pour 1 BLOQUE pour 2 pour FERME
	 */
	@Column
	private CompteStatus etat;
	@ManyToOne
	private Client client;
	@OneToMany
	@JoinColumn(name = "cpt_id")
	private List<Operation> opeartion;

}
