package fr.solinum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.solinum.entities.Operation;

@Repository
@Transactional

public class DaoOperationImplementation implements DaoOperation {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Operation ajouterOperation(Operation operation) {

		entityManager.persist(operation);
		return operation;
	}

	@Override
	public List<Operation> consulterOperations(String codeCompte) {

		Query query = entityManager
				.createQuery("select operations from Operation operations where operations.compte.numCompte like :codeCompte ")
				.setParameter("codeCompte", codeCompte);
		List<Operation> operations = query.getResultList();
		return operations;
	}
}
