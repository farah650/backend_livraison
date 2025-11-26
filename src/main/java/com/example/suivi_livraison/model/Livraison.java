package com.example.suivi_livraison.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse; // <== doit correspondre à getAdresse/setAdresse
    private LocalDate dateLivraison;
    private String statut;
    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client; 
    private String codeBarre; 
    // Constructeurs
    public Livraison() {}
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public LocalDate getDateLivraison() { return dateLivraison; }
    public void setDateLivraison(LocalDate dateLivraison) { this.dateLivraison = dateLivraison; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public Livreur getLivreur() { return livreur; }
    public void setLivreur(Livreur livreur) { this.livreur = livreur; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public String getCodeBarre(){return codeBarre;}
    public void setCodeBarre(String codeBarre){this.codeBarre=codeBarre;}
    
}

