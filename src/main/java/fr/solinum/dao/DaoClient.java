package fr.solinum.dao;

import java.util.List;

import fr.solinum.entities.Client;

public interface DaoClient {

	public Client ajouterClient(Client client);

	public void supprimerClient(long idClient);

	public void modifierClient(Client client); 	// je vais voir si on va retourner un client modifié

	public List<Client> consulterListeClients();

}
