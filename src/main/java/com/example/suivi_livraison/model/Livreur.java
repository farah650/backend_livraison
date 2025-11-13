package com.example.suivi_livraison.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Livreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    private boolean actif = true; // par défaut le livreur est actif

    private double noteMoyenne = 0.0; // note par défaut 0.0
}
