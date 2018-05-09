package fr.solinum.test;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import fr.solinum.dao.DaoClient;
import fr.solinum.dao.DaoCompte;
import fr.solinum.dao.DaoOperation;
import fr.solinum.entities.Client;
import fr.solinum.entities.Compte;
import fr.solinum.entities.CompteCourant;
import fr.solinum.entities.CompteEpargne;
import fr.solinum.entities.Retrait;
import fr.solinum.entities.Versement;


@EntityScan(basePackages = { "fr.solinum.entities" })
@ComponentScan(basePackages = { "fr.solinum.dao" })
@ComponentScan(basePackages= {"fr.solinum.metier"})
@ComponentScan(basePackages= {"fr.solinum.web"})
@SpringBootApplication
public class GestionComptes1Application implements CommandLineRunner {

	@Autowired
	private DaoClient daoClient;
	@Autowired
	private DaoCompte daoCompte; // ici on veut avoir une interface generique
	@Autowired
	private DaoOperation daoOperation;
	public static void main(String[] args) {
		SpringApplication.run(GestionComptes1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Client client1 = daoClient.ajouterClient(new Client("Ali", "belhadj.ali@gmail.com"));
		Client client2 = daoClient.ajouterClient(new Client("Rabii", "belhadj.rabii@gmail.com"));
		Client client3 = daoClient.ajouterClient(new Client("Abdessalem", "belhadj.abdessalem@gmail.com"));

		Compte cp1=daoCompte.ajouterCompte(new CompteCourant("compte1", new Date(), 9000, client1, 900));
		Compte cp2=daoCompte.ajouterCompte(new CompteEpargne("compte2", new Date(), 8000, client1, 59));
		Compte cp3=daoCompte.ajouterCompte(new CompteCourant("compte3", new Date(), 7000, client2, 800));
		Compte cp4=daoCompte.ajouterCompte(new CompteEpargne("compte4", new Date(), 60000, client3, 17));
		
		//operation sur le compte 1
		daoOperation.ajouterOperation(new Versement(5000, new Date(), cp1));
		daoOperation.ajouterOperation(new Retrait(2000, new Date(), cp1));
		daoOperation.ajouterOperation(new Versement(50, new Date(), cp1));
		daoOperation.ajouterOperation(new Versement(8000, new Date(), cp1));
		
		//operation sur le compte 2
		daoOperation.ajouterOperation(new Versement(5000, new Date(), cp2));
		daoOperation.ajouterOperation(new Retrait(2000, new Date(), cp2));
		daoOperation.ajouterOperation(new Versement(50, new Date(), cp2));
		daoOperation.ajouterOperation(new Versement(8000, new Date(), cp2));
		
		//operation sur le compte 3
		daoOperation.ajouterOperation(new Versement(5000, new Date(), cp3));
		daoOperation.ajouterOperation(new Retrait(2000, new Date(), cp3));
		daoOperation.ajouterOperation(new Versement(50, new Date(), cp3));
		daoOperation.ajouterOperation(new Versement(8000, new Date(), cp3));
		
		//operation sur le compte 4
		daoOperation.ajouterOperation(new Versement(5000, new Date(), cp4));
		daoOperation.ajouterOperation(new Retrait(2000, new Date(), cp4));
		daoOperation.ajouterOperation(new Versement(50, new Date(), cp4));
		daoOperation.ajouterOperation(new Versement(8000, new Date(), cp4));
				
	}
}
