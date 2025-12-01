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
    private byte[] image; 

    private String type; 

    private LocalDateTime dateDepot = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "livraison_id", nullable = false)
    private Livraison livraison;
    
    @OneToOne
    private Position position;
    private Double latitude;
    private Double longitude;
}
