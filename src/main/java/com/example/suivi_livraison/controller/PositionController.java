package com.example.suivi_livraison.controller;

import com.example.suivi_livraison.DTO.PositionDTO;
import com.example.suivi_livraison.Services.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * Contrôleur pour accéder aux positions des LIVREURS
 * Utilisé par les ADMINS pour voir les positions des livreurs
 * 
 * ❌ NOTES: Les CLIENTs ne devraient pas utiliser cet endpoint directement
 * Ils doivent utiliser /api/livraisons/{id}/tracking pour voir le tracking de
 * leurs livraisons
 * 
 * ✅ SÉCURITÉ: Protégé par SecurityConfig (/api/positions/** = ADMIN seulement)
 */
@RestController
@RequestMapping("/api/positions")
public class PositionController {

    @Autowired
    private LivreurService livreurService;

    /**
     * Récupérer la dernière position d'un livreur (ADMIN seulement)
     * 
     * @param livreurId ID du livreur
     * @return Position actuelle du livreur
     */
    @GetMapping("/livreur/{livreurId}/latest")
    public ResponseEntity<PositionDTO> getLatestPosition(
            @PathVariable Long livreurId) {
        try {
            List<PositionDTO> positions = livreurService.getPositions(livreurId);
            if (positions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(positions.get(0));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Récupérer l'historique des positions d'un livreur (ADMIN seulement)
     * 
     * @param livreurId ID du livreur
     * @return Liste de toutes les positions
     */
    @GetMapping("/livreur/{livreurId}/history")
    public ResponseEntity<List<PositionDTO>> getPositionHistory(
            @PathVariable Long livreurId) {
        try {
            List<PositionDTO> positions = livreurService.getPositions(livreurId);
            return ResponseEntity.ok(positions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
