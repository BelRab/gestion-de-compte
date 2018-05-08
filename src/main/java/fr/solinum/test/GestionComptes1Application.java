package fr.solinum.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
@EntityScan(basePackages= {"fr.solinum.entities"})
@SpringBootApplication
public class GestionComptes1Application {

	public static void main(String[] args) {
		SpringApplication.run(GestionComptes1Application.class, args);
	}
}
