package fr.solinum.dao;

import java.util.List;

import fr.solinum.entities.Operation;

public interface DaoOperation {

	public Operation ajouterOperation(Operation operation);
	public List<Operation> consulterOperations(String codeCompte);
}
