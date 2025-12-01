package com.example.suivi_livraison.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;
@Data
@Entity
    public class Livreur extends Utilisateur {

    private String vehicleInfo;
    private String typeVehicule;
    private String driverStatut;
    private Double note = 0.0;

    private boolean actif = true;

    @OneToMany(mappedBy = "livreur")
    private List<Livraison> livraisons;

    @OneToMany(mappedBy = "livreur")
    private List<Colis> colis;
}