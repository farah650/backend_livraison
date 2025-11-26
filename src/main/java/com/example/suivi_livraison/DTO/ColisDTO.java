package com.example.suivi_livraison.DTO;

public class ColisDTO {
    private Long id;
    private String codeBarres;
    private String statut;

    private Long clientId;
    private Long livreurId;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodeBarres() { return codeBarres; }
    public void setCodeBarres(String codeBarres) { this.codeBarres = codeBarres; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }

    public Long getLivreurId() { return livreurId; }
    public void setLivreurId(Long livreurId) { this.livreurId = livreurId; }
}
