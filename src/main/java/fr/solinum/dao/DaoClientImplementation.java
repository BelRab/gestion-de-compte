package fr.solinum.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.solinum.entities.Client;

@Repository
@Transactional

public class DaoClientImplementation implements DaoClient {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Client ajouterClient(Client client) {
		entityManager.persist(client);
		return client;
	}

	@Override
	public void supprimerClient(long codeClient) {
		// pour supprimer il faut chercher s'il existe
		Client client = entityManager.find(Client.class, codeClient);
		// si on a trouvé on va le supprimer
		entityManager.remove(client);
	}

	@Override
	public void modifierClient(Client client) {
		entityManager.merge(client);
	}

	@Override
	public List<Client> consulterListeClients() {
		Query query = entityManager.createQuery("select clients from Client clients");
		List<Client> listeClients = query.getResultList();
		return listeClients;
	}
}
