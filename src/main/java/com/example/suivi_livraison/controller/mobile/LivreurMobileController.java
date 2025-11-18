package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.Services.LivreurService;
import com.example.suivi_livraison.dto.PositionDTO;
import com.example.suivi_livraison.model.Livreur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobile/livreur")
public class LivreurMobileController {

    @Autowired
    private LivreurService livreurService;

    @PostMapping("/login")
    public ResponseEntity<Livreur> login(@RequestParam String email, @RequestParam String password) {
        Livreur livreur = livreurService.authenticate(email, password);
        return livreur != null ? ResponseEntity.ok(livreur) : ResponseEntity.status(401).build();
    }

    @PostMapping("/position/{livreurId}")
    public ResponseEntity<PositionDTO> addPosition(
            @PathVariable Long livreurId,
            @RequestParam double latitude,
            @RequestParam double longitude) {
        var position = livreurService.addPosition(livreurId, latitude, longitude);
        PositionDTO dto = new PositionDTO();
        dto.setLatitude(position.getLatitude());
        dto.setLongitude(position.getLongitude());
        dto.setDateHeure(position.getDateHeure());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/positions/{livreurId}")
    public ResponseEntity<List<PositionDTO>> getPositions(@PathVariable Long livreurId) {
        return ResponseEntity.ok(livreurService.getPositions(livreurId));
    }
}