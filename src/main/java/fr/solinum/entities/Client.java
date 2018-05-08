package fr.solinum.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Client implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codeClient;
	private String nomClient;
	private String emailClient;
	@OneToMany(mappedBy="client")
	private List<Compte> comptes;

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String nomClient, String emailClient) {
		super();
		this.nomClient = nomClient;
		this.emailClient = emailClient;
	}

	/**
	 * @return the nomClient
	 */
	public String getNomClient() {
		return nomClient;
	}

	/**
	 * @param nomClient the nomClient to set
	 */
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	/**
	 * @return the emailClient
	 */
	public String getEmailClient() {
		return emailClient;
	}

	/**
	 * @param emailClient the emailClient to set
	 */
	public void setEmailClient(String emailClient) {
		this.emailClient = emailClient;
	}

	/**
	 * @return the comptes
	 */
	public List<Compte> getComptes() {
		return comptes;
	}

	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
}
