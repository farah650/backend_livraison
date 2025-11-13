package com.example.suivi_livraison.controller;
import com.example.suivi_livraison.model.Livraison;
import com.example.suivi_livraison.repository.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonRepository livraisonRepository;

    // GET all livraisons
    @GetMapping
    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    // GET livraison by id
    @GetMapping("/{id}")
    public ResponseEntity<Livraison> getLivraisonById(@PathVariable Long id) {
        return livraisonRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new livraison
    @PostMapping
    public Livraison createLivraison(@RequestBody Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    // PUT update livraison
    @PutMapping("/{id}")
    public ResponseEntity<Livraison> updateLivraison(@PathVariable Long id, @RequestBody Livraison livraisonDetails) {
        return livraisonRepository.findById(id).map(livraison -> {
            livraison.setAdresse(livraisonDetails.getAdresse());
            livraison.setDateLivraison(livraisonDetails.getDateLivraison());
            livraison.setStatut(livraisonDetails.getStatut());
            Livraison updated = livraisonRepository.save(livraison);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE livraison
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivraison(@PathVariable Long id) {
        return livraisonRepository.findById(id).map(livraison -> {
            livraisonRepository.delete(livraison);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
