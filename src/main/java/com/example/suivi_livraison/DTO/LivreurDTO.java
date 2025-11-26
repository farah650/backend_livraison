package com.example.suivi_livraison.DTO;

public class LivreurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String vehicleInfo;
    private String driverStatut;
    private Double note;
    private boolean actif;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    
    public String getVehicleInfo() { return vehicleInfo; }
    public void setVehicleInfo(String vehicleInfo) { this.vehicleInfo = vehicleInfo; }
    
    public String getDriverStatut() { return driverStatut; }
    public void setDriverStatut(String driverStatut) { this.driverStatut = driverStatut; }
    
    public Double getNote() { return note; }
    public void setNote(Double note) { this.note = note; }
    
    public boolean isActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }
}
