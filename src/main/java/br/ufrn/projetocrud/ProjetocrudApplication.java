package br.ufrn.projetocrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"br.ufrn.projetocrud.dominio"})
@SpringBootApplication
public class ProjetocrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetocrudApplication.class, args);
	}

}
