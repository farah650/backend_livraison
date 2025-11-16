package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.model.Notification;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.DTO.LoginRequest;
import com.example.suivi_livraison.Services.LivreurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mobile/livreur")
public class LivreurMobileController {

    @Autowired
    private LivreurService livreurService;

  
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<Livreur> livreurOpt = livreurService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (livreurOpt.isPresent()) {
            return ResponseEntity.ok(livreurOpt.get());
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }


    @PostMapping("/position")
    public ResponseEntity<Position> updatePosition(@RequestParam Long idLivreur, @RequestParam double latitude, @RequestParam double longitude) {
        Position pos = livreurService.addPosition(idLivreur, latitude, longitude);
        if (pos != null) {
            return ResponseEntity.ok(pos);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/positions/{idLivreur}")
    public ResponseEntity<List<Position>> getPositions(@PathVariable Long idLivreur) {
        List<Position> positions = livreurService.getPositions(idLivreur);
        return ResponseEntity.ok(positions);
    }

    @GetMapping("/notifications/{idLivreur}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long idLivreur) {
        List<Notification> notifications = livreurService.getNotifications(idLivreur);
        return ResponseEntity.ok(notifications);
    }

    
}
