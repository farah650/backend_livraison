package com.example.suivi_livraison.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScanRequest {
    private Long idLivreur;
    private Long idLivraison;
    private String codeBarreScanne;
    
}
