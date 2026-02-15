package com.example.suivi_livraison.DTO;

import java.time.LocalDate;

public class LivraisonDTO {
    private Long id;
    private String adresse;
    private LocalDate dateLivraison;
    private String statut;
    private String codeBarre;
    
    // Infos livreur sans récursion
    private LivreurDTO livreur;
    
    // Infos client sans récursion
    private ClientDTO client;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    
    public LocalDate getDateLivraison() { return dateLivraison; }
    public void setDateLivraison(LocalDate dateLivraison) { this.dateLivraison = dateLivraison; }
    
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    
    public String getCodeBarre() { return codeBarre; }
    public void setCodeBarre(String codeBarre) { this.codeBarre = codeBarre; }
    
    public LivreurDTO getLivreur() { return livreur; }
    public void setLivreur(LivreurDTO livreur) { this.livreur = livreur; }
    
    public ClientDTO getClient() { return client; }
    public void setClient(ClientDTO client) { this.client = client; }
}
