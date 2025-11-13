package com.example.suivi_livraison.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password; // mot de passe hashé
    private String role; // "CLIENT", "LIVREUR", "ADMIN"
}

