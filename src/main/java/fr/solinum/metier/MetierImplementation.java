package fr.solinum.metier;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.solinum.dao.DaoCompte;
import fr.solinum.dao.DaoOperation;
import fr.solinum.entities.Compte;
import fr.solinum.entities.CompteCourant;
import fr.solinum.entities.Operation;
import fr.solinum.entities.Retrait;
import fr.solinum.entities.Versement;

@Service
@Transactional // on va voir si on peut l'enlever

public class MetierImplementation implements InterfaceMetier {

	@Autowired // faire l'instanciation c'est à dire l'implementation
	DaoCompte daoCompte;

	@Autowired
	DaoOperation daoOperation;

	@Override
	public Compte consulterCompte(String idCompte) {

		Compte compte = daoCompte.consulterUnCompte(idCompte);

		if (compte == null) {

			throw new RuntimeException("Le compte n'existe pas !");

		}

		return compte;
	}

	@Override
	public void verser(String codeCompte, double montant) {

		Compte compte = daoCompte.consulterUnCompte(codeCompte);

		if (montant > 0) {

			Versement versement = new Versement(montant, new Date(), compte);

			daoOperation.ajouterOperation(versement);

			compte.setSolde(compte.getSolde() + montant);

			daoCompte.misAJourCompte(compte);

		} else {

			throw new RuntimeException(String.format("Le montant % n'est pas valide!", montant));
		}
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte compte = daoCompte.consulterUnCompte(codeCompte);

		if (compte instanceof CompteCourant) {
			if (montant < compte.getSolde() + ((CompteCourant) compte).getDecouvert()) {

				Retrait retrait = new Retrait(montant, new Date(), compte);

				daoOperation.ajouterOperation(retrait);

				compte.setSolde(compte.getSolde() - montant);

				daoCompte.misAJourCompte(compte);
			} else {

				throw new RuntimeException(
						String.format("Solde insuffisant , le montant % superieur au solde", montant));

			}
		} else {

			if (montant < compte.getSolde()) {

				Retrait retrait = new Retrait(montant, new Date(), compte);

				daoOperation.ajouterOperation(retrait);

				compte.setSolde(compte.getSolde() - montant);

				daoCompte.misAJourCompte(compte);
			} else {

				throw new RuntimeException(
						String.format("Solde insuffisant , le montant % superieur au solde", montant));

			}
		}
	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double montant) {

		retirer(codeCompte1,montant);
		
		verser(codeCompte2, montant);
	}

	@Override
	public List<Operation> listeOperation(String codeCompte) {
	
		List<Operation> operations=daoOperation.consulterOperations(codeCompte);
		
		return operations;
	}

}
