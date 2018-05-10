package fr.solinum.dao;

import fr.solinum.entities.Compte;

public interface DaoCompte {
	 
	public Compte ajouterCompte(Compte compte);
	public Compte consulterUnCompte(String idCompte);
	public void misAJourCompte(Compte compte);
}
