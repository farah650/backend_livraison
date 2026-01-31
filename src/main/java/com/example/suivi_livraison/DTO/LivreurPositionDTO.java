package com.example.suivi_livraison.DTO;

import java.time.LocalDateTime;

public class LivreurPositionDTO {
    private Long livreurId;
    private String nom;
    private Double latitude;
    private Double longitude;
    private LocalDateTime dateHeure;

    public Long getLivreurId() { return livreurId; }
    public void setLivreurId(Long livreurId) { this.livreurId = livreurId; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
}