package fr.solinum.metier;

import java.util.List;

import fr.solinum.entities.Compte;
import fr.solinum.entities.Operation;

public interface InterfaceMetier {
	
	public Compte consulterCompte(String idCompte);
	public void verser(String codeCompte , double montant);
	public void retirer (String codeCompte , double montant);
	public void virement(String codeCompte1, String codeCompte2 , double montant);
	public List<Operation> listeOperation(String codeCompte);

}
