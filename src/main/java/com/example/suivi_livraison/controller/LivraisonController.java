package com.example.suivi_livraison.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.suivi_livraison.Services.LivraisonService;
import com.example.suivi_livraison.DTO.LivraisonDTO;
import com.example.suivi_livraison.DTO.TrackingDTO;
import com.example.suivi_livraison.model.Livraison;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping("/api/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @GetMapping
    public List<LivraisonDTO> getAll() { 
        return livraisonService.getAll(); 
    }
    @GetMapping("/{id}")
    public LivraisonDTO getById(@PathVariable Long id) { 
        return livraisonService.getById(id); 
    }
    @PostMapping
    public LivraisonDTO create(@RequestBody LivraisonDTO dto) {
        return livraisonService.create(dto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Livraison> updateLivraison(
      @PathVariable Long id, 
      @RequestBody LivraisonDTO dto
    ) {
      try {
        Livraison updated = livraisonService.update(id, dto);
        return ResponseEntity.ok(updated);
      } catch (Exception e) {
        return ResponseEntity.badRequest().build();
      }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        livraisonService.delete(id);
    }

    @GetMapping("/client/{id}")
    public List<LivraisonDTO> getByClient(@PathVariable Long id) {
        return livraisonService.getByClient(id);
    }

    
    @GetMapping("/livreur/{id}")
    public List<LivraisonDTO> getByLivreur(@PathVariable Long id) {
        return livraisonService.getByLivreur(id);
    }
    
    @GetMapping("/{id}/tracking")
    public ResponseEntity<TrackingDTO> getTracking(@PathVariable Long id) {
        try {
            TrackingDTO tracking = livraisonService.getTrackingForClient(id);
            return ResponseEntity.ok(tracking);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

