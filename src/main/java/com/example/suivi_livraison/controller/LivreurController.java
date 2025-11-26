package com.example.suivi_livraison.controller;
import com.example.suivi_livraison.DTO.LivreurDTO;
import com.example.suivi_livraison.model.Livreur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.suivi_livraison.Services.LivreurService;
@RestController
@RequestMapping("/api/livreurs")
public class LivreurController {

    @Autowired
    private LivreurService livreurService;

    @GetMapping
    public List<LivreurDTO> getAll() {
        return livreurService.getAll();
    }

    @GetMapping("/{id}")
    public LivreurDTO getById(@PathVariable Long id) {
        return livreurService.findById(id);
    }

    @PostMapping
    public Livreur create(@RequestBody Livreur livreur) {
        return livreurService.create(livreur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        livreurService.delete(id);
    }
}
