
package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.Services.LivraisonService;
import com.example.suivi_livraison.DTO.LivraisonDTO;
import com.example.suivi_livraison.model.Livraison;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile/livraison")
public class LivraisonMobileController {

    @Autowired
    private LivraisonService livraisonService;

    @GetMapping("/assignees/{idLivreur}")
    public ResponseEntity<List<LivraisonDTO>> getAssignedDeliveries(@PathVariable Long idLivreur) {
        List<LivraisonDTO> livraisons = livraisonService.getAssignedDeliveries(idLivreur);
        return ResponseEntity.ok(livraisons);
    }

    @PutMapping("/statut")
    public ResponseEntity<?> updateStatus(
        @RequestParam Long idLivraison,
        @RequestParam String nouveauStatut
    ) {
        boolean success = livraisonService.updateStatut(idLivraison, nouveauStatut);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Erreur statut");
        }
    }

    @GetMapping("/historique/{idLivreur}")
    public ResponseEntity<List<LivraisonDTO>> getHistory(@PathVariable Long idLivreur) {
        List<LivraisonDTO> historique = livraisonService.getHistory(idLivreur);
        return ResponseEntity.ok(historique);
    }
}
