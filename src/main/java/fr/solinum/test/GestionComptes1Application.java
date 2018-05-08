package fr.solinum.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import fr.solinum.dao.DaoClient;
import fr.solinum.entities.Client;

@EntityScan(basePackages = { "fr.solinum.entities" })
@ComponentScan(basePackages = { "fr.solinum.dao" })
@SpringBootApplication
public class GestionComptes1Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(GestionComptes1Application.class, args);
		DaoClient daoClient = context.getBean(DaoClient.class);
		daoClient.ajouterClient(new Client("Ali", "belhadj.ali@gmail.com"));
		daoClient.ajouterClient(new Client("Rabii", "belhadj.rabii@gmail.com"));
		daoClient.ajouterClient(new Client("Abdessalem", "belhadj.abdessalem@gmail.com"));
	}
}
