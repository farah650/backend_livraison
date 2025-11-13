package com.example.suivi_livraison.controller;
import com.example.suivi_livraison.model.Colis;
import com.example.suivi_livraison.repository.ColisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colis")
public class ColisController {

    @Autowired
    private ColisRepository colisRepository;

    // 🔹 GET all colis
    @GetMapping
    public List<Colis> getAllColis() {
        return colisRepository.findAll();
    }

    // 🔹 GET colis by ID
    @GetMapping("/{id}")
    public ResponseEntity<Colis> getColisById(@PathVariable Long id) {
        return colisRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 GET colis by client
    @GetMapping("/client/{clientId}")
    public List<Colis> getColisByClient(@PathVariable Long clientId) {
        return colisRepository.findByClientId(clientId);
    }

    // 🔹 GET colis by livreur
    @GetMapping("/livreur/{livreurId}")
    public List<Colis> getColisByLivreur(@PathVariable Long livreurId) {
        return colisRepository.findByLivreurId(livreurId);
    }

    // 🔹 GET colis by statut
    @GetMapping("/statut/{statut}")
    public List<Colis> getColisByStatut(@PathVariable String statut) {
        return colisRepository.findByStatut(statut);
    }

    // 🔹 POST create new colis
    @PostMapping
    public Colis createColis(@RequestBody Colis colis) {
        return colisRepository.save(colis);
    }

    // 🔹 PUT update colis
    @PutMapping("/{id}")
    public ResponseEntity<Colis> updateColis(@PathVariable Long id, @RequestBody Colis colisDetails) {
        return colisRepository.findById(id)
                .map(colis -> {
                    colis.setCodeBarres(colisDetails.getCodeBarres());
                    colis.setStatut(colisDetails.getStatut());
                    colis.setClient(colisDetails.getClient());
                    colis.setLivreur(colisDetails.getLivreur());
                    Colis updated = colisRepository.save(colis);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // 🔹 DELETE colis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColis(@PathVariable Long id) {
        return colisRepository.findById(id)
                .map(colis -> {
                    colisRepository.delete(colis);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
