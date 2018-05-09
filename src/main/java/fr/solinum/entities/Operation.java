package fr.solinum.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@DiscriminatorColumn(name="Type_Operation")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Operation implements Serializable {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long numOperation;
	private Date dateOperation;
	private double montant;
	@ManyToOne
	@JoinColumn(name="num_compte")
	private Compte compte;

	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operation(double montant,Date dateOperation,  Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}



	/**
	 * @return the dateOperation
	 */
	public Date getDateOperation() {
		return dateOperation;
	}

	/**
	 * @param dateOperation the dateOperation to set
	 */
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

	
}
