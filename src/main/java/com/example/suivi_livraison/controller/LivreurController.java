package com.example.suivi_livraison.controller;
import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {

    @Autowired
    private LivreurRepository livreurRepository;

    // GET all livreurs
    @GetMapping
    public List<Livreur> getAllLivreurs() {
        return livreurRepository.findAll();
    }

    // GET livreur by id
    @GetMapping("/{id}")
    public ResponseEntity<Livreur> getLivreurById(@PathVariable Long id) {
        return livreurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST create new livreur
    @PostMapping
    public Livreur createLivreur(@RequestBody Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    // PUT update livreur
    @PutMapping("/{id}")
    public ResponseEntity<Livreur> updateLivreur(@PathVariable Long id, @RequestBody Livreur livreurDetails) {
        return livreurRepository.findById(id).map(livreur -> {
            livreur.setNom(livreurDetails.getNom());
            livreur.setPrenom(livreurDetails.getPrenom());
            livreur.setEmail(livreurDetails.getEmail());
            livreur.setTelephone(livreurDetails.getTelephone());
            livreur.setActif(livreurDetails.isActif());
            livreur.setNoteMoyenne(livreurDetails.getNoteMoyenne());
            return ResponseEntity.ok(livreurRepository.save(livreur));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE livreur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivreur(@PathVariable Long id) {
        return livreurRepository.findById(id).map(livreur -> {
            livreurRepository.delete(livreur);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
