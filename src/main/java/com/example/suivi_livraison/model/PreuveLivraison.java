package com.example.suivi_livraison.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class PreuveLivraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "image", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] image; // Stocke l'image en binaire

    private String type; // MIME type ("image/jpeg", "image/png"…)

    private LocalDateTime dateDepot = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "livraison_id", nullable = false)
    private Livraison livraison;

}
