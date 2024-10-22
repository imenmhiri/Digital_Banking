package com.fss.Digital.Banking.entites;

import java.io.Serializable;
import java.util.Date;

import com.fss.Digital.Banking.enums.TypeOperation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class Operation implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	@Column
	private Date dateOperation; 
	@Column
	private double montant;
	@Enumerated(EnumType.STRING)
	@Column
	private TypeOperation type;
	@ManyToOne
	private CompteBancaire compteBancaire; 
	
	
}
