package com.fss.Digital.Banking.entites;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@DiscriminatorValue("Epargne")
public class CompteEpargne extends CompteBancaire implements Serializable  {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column
	private double tauxInteret;

}
