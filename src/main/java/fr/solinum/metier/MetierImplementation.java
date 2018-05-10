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
import fr.solinum.entities.CompteEpargne;
import fr.solinum.entities.Operation;
import fr.solinum.entities.Retrait;
import fr.solinum.entities.Versement;
import fr.solinum.exception.FormsValidationException;

@Service
@Transactional // on va voir si on peut l'enlever

public class MetierImplementation implements InterfaceMetier {

	@Autowired // faire l'instanciation c'est à dire l'implementation
	DaoCompte daoCompte;

	@Autowired
	DaoOperation daoOperation;

	@Override
	public Compte consulterCompte(String idCompte) throws FormsValidationException {

		if (idCompte.equals("")) {
			throw new FormsValidationException("Le champ saisi est vide.");
		}

		Compte compte = daoCompte.consulterUnCompte(idCompte);

		if (compte == null) {

			throw new FormsValidationException(String.format("Le compte saisie [ %s ] n'existe pas .", idCompte));

		}

		return compte;
	}

	@Override
	public void verser(String codeCompte, double montant) throws FormsValidationException {

		Compte compte = daoCompte.consulterUnCompte(codeCompte);
		
		if (compte == null) {

			throw new FormsValidationException(String.format("Le compte saisie [ %s ] n'existe pas .", codeCompte));

		}

		if (montant > 0) {

			Versement versement = new Versement(montant, new Date(), compte);

			daoOperation.ajouterOperation(versement);

			compte.setSolde(compte.getSolde() + montant);

			daoCompte.misAJourCompte(compte);

		} else {

			throw new FormsValidationException(String.format("Le montant saisie [ %s ] n'est pas valide!", montant));
		}
	}

	@Override
	public void retirer(String codeCompte, double montant) throws FormsValidationException {

		Compte compte = daoCompte.consulterUnCompte(codeCompte);
		
		if (compte == null) {

			throw new FormsValidationException(String.format("Le compte saisie [ %s ] n'existe pas .", codeCompte));

		}
		
		if (compte instanceof CompteCourant) {

			if (montant > 0) {		// si le montant positif
			
				if (montant < compte.getSolde() + ((CompteCourant) compte).getDecouvert()) {		// 	si le solde est superieur au solde

					Retrait retrait = new Retrait(montant, new Date(), compte);

					daoOperation.ajouterOperation(retrait);

					compte.setSolde(compte.getSolde() - montant);

					daoCompte.misAJourCompte(compte);
				} else {

					throw new FormsValidationException(
							String.format("Solde insuffisant , le montant % superieur au solde.", montant));
				}
			} else { // si le montant est negatif

				throw new FormsValidationException(String.format("Le montant [ %s ] saisie est invalide.", montant));
			}

		} else if (compte instanceof CompteEpargne) { // si le compte est compte epargne

			if (montant > 0) {
				if (montant < compte.getSolde()) {

					Retrait retrait = new Retrait(montant, new Date(), compte);

					daoOperation.ajouterOperation(retrait);

					compte.setSolde(compte.getSolde() - montant);

					daoCompte.misAJourCompte(compte);
				} else {

					throw new FormsValidationException(
							String.format("Solde insuffisant , le montant % superieur au solde", montant));
				}

			}else {

				throw new FormsValidationException(String.format("Le montant [ %s ] saisie est invalide.", montant));
				
			}
		}
	}

	@Override
	public void virement(String codeCompte1, String codeCompte2, double montant) throws FormsValidationException {

		if(codeCompte1.equals(codeCompte2)) {
			throw new FormsValidationException("Impossible de faire le virement vers le meme compte.");
		}
		if(codeCompte2.equals("")) {
			throw new FormsValidationException(String.format("Le compte [ %s ] ne doit pas etre vide", codeCompte2));
		}
		
		retirer(codeCompte1, montant);

		verser(codeCompte2, montant);
	}

	@Override
	public List<Operation> listeOperation(String codeCompte) {

		List<Operation> operations = daoOperation.consulterOperations(codeCompte);

		return operations;
	}

}
