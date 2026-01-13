
package com.example.suivi_livraison.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrackingDTO {
   private double livreurLat;
    private double livreurLng;
    private String adresseClient;
    

}
