package com.example.suivi_livraison.controller;

import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.Services.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livreurs")
public class LivreurController {

    @Autowired
    private LivreurService service;

    @GetMapping("/{id}")
    public Livreur getLivreur(@PathVariable Long id) {
        Livreur livreur = service.findById(id);
        if (livreur != null) {
            System.out.println("Note moyenne: " + livreur.getNoteMoyenne());
        }
        return livreur;
    }
}