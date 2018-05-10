package fr.solinum.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.solinum.entities.Compte;

@Repository
@Transactional
public class DaoCompteImplementation implements DaoCompte{

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Compte ajouterCompte(Compte compte) {
		entityManager.persist(compte);
		return compte;
	}
	@Override
	public Compte consulterUnCompte(String idCompte) {

			Compte compte = entityManager.find(Compte.class, idCompte);

			return compte;
	}
	@Override
	public void misAJourCompte(Compte compte) {

		entityManager.merge(compte);
	}

}
