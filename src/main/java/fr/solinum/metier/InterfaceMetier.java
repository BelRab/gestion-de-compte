package fr.solinum.metier;

import java.util.List;

import fr.solinum.entities.Compte;
import fr.solinum.entities.Operation;
import fr.solinum.exception.FormsValidationException;

public interface InterfaceMetier {
	
	public Compte consulterCompte(String idCompte) throws FormsValidationException;
	public void verser(String codeCompte , double montant) throws FormsValidationException;
	public void retirer (String codeCompte , double montant) throws FormsValidationException;
	public void virement(String codeCompte1, String codeCompte2 , double montant) throws FormsValidationException;
	public List<Operation> listeOperation(String codeCompte);

}
