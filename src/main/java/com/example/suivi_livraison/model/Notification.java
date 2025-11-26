package com.example.suivi_livraison.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_envoi")
    private LocalDateTime dateEnvoi = LocalDateTime.now();

    private String destinataire;
    private String message;
    private boolean lue = false;
    private String type;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDateEnvoi() { return dateEnvoi; }
    public void setDateEnvoi(LocalDateTime dateEnvoi) { this.dateEnvoi = dateEnvoi; }
    public String getDestinataire() { return destinataire; }
    public void setDestinataire(String destinataire) { this.destinataire = destinataire; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isLue() { return lue; }
    public void setLue(boolean lue) { this.lue = lue; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
     public Utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; }
}