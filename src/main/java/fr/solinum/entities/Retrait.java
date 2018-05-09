package fr.solinum.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("retrait")
public class Retrait extends Operation{

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(double montant, Date dateOperation, Compte compte) {
		super(montant, dateOperation, compte);
		// TODO Auto-generated constructor stub
	}

	
	

}
