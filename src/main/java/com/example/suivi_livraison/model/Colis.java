package com.example.suivi_livraison.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeBarres;
    private String statut; // "En attente", "En cours", "Livré"

    @ManyToOne
    private Client client;

    @ManyToOne
    private Livreur livreur;
}
