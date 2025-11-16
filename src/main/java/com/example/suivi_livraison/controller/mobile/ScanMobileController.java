
package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.DTO.ScanRequest;
import com.example.suivi_livraison.Services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile/scan")
public class ScanMobileController {
    @Autowired
    private LivraisonService livraisonService;

    @PostMapping
    public ResponseEntity<?> scan(@RequestBody ScanRequest req) {
        boolean ok = livraisonService.validerScan(req.getIdLivraison(), req.getIdLivreur(), req.getCodeBarreScanne());
        if(ok){
            return ResponseEntity.ok("Colis validé");
        } else {
            return ResponseEntity.badRequest().body("Mauvais code-barre ou non-autorisé");
        }
    }
}
