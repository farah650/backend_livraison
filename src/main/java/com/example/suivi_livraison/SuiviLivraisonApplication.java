package com.example.suivi_livraison;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuiviLivraisonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuiviLivraisonApplication.class, args);
                System.out.println(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().encode("password"));
	}

}
