package com.example.suivi_livraison.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String destinataire; // "Client" ou "Livreur"

    private LocalDateTime dateEnvoi = LocalDateTime.now(); // date par défaut à la création
}
