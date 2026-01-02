package com.example.suivi_livraison.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Client extends Utilisateur {

    private String adresse;

    @OneToMany(mappedBy = "client")
    private List<Livraison> livraisons;

    @OneToMany(mappedBy = "client")
    private List<Colis> colis;
}
