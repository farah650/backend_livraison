package com.example.suivi_livraison.controller;
import com.example.suivi_livraison.model.Livraison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.suivi_livraison.Services.LivraisonService;
import com.example.suivi_livraison.DTO.LivraisonDTO;
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
}

