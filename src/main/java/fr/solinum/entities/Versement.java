package fr.solinum.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("versement")
public class Versement extends Operation{

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versement(double montant, Date dateOperation) {
		super(montant, dateOperation);
		// TODO Auto-generated constructor stub
	}
	
	

}
