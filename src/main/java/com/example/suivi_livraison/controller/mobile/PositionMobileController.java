package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.DTO.PositionDTO;
import com.example.suivi_livraison.DTO.PositionRequest;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.Services.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

/**
 * Contrôleur pour gérer les positions du LIVREUR en temps réel
 * Endpoints utilisés par l'app mobile du livreur
 * 
 * ✅ SÉCURITÉ: Protégé par SecurityConfig (/mobile/positions/** = LIVREUR +
 * ADMIN)
 */
@RestController
@RequestMapping("/mobile/positions")
public class PositionMobileController {

    @Autowired
    private LivreurService livreurService;

    /**
     * Enregistrer la position GPS du livreur en temps réel
     * Appelé régulièrement depuis l'app mobile (toutes les 30s environ)
     * 
     * @param livreurId       ID du livreur qui enregistre sa position
     * @param positionRequest Latitude et longitude
     * @return Position enregistrée
     */
    @PostMapping("/record")
    public ResponseEntity<Position> recordPosition(
            @RequestParam Long livreurId,
            @RequestBody PositionRequest positionRequest) {
        try {
            Position position = livreurService.addPosition(
                    livreurId,
                    positionRequest.getLatitude(),
                    positionRequest.getLongitude());
            return ResponseEntity.ok(position);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Récupérer tout l'historique des positions d'un livreur
     * 
     * @param livreurId ID du livreur
     * @return Liste de toutes les positions enregistrées (triées par date
     *         décroissante)
     */
    @GetMapping("/history")
    public ResponseEntity<List<PositionDTO>> getPositionHistory(
            @RequestParam Long livreurId) {
        try {
            List<PositionDTO> positions = livreurService.getPositions(livreurId);
            return ResponseEntity.ok(positions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Récupérer la DERNIÈRE position du livreur (position actuelle)
     * Utilisé par le dashboard du livreur pour voir où il est
     * 
     * @param livreurId ID du livreur
     * @return La position la plus récente
     */
    @GetMapping("/latest")
    public ResponseEntity<PositionDTO> getLatestPosition(
            @RequestParam Long livreurId) {
        try {
            List<PositionDTO> positions = livreurService.getPositions(livreurId);
            if (positions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(positions.get(0)); // Première = la plus récente
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
