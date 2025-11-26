package com.example.suivi_livraison.DTO;

import java.time.LocalDateTime;

public class PositionDTO {
    private double latitude;
    private double longitude;
    private LocalDateTime dateHeure;

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
}
