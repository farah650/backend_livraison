package com.example.suivi_livraison.controller;
import com.example.suivi_livraison.DTO.ColisDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.suivi_livraison.Services.ColisService;
@RestController
@RequestMapping("/api/colis")
public class ColisController {

    @Autowired
    private ColisService colisService;

    @GetMapping
    public List<ColisDTO> getAll() { 
        return colisService.getAll(); 
    }

    @GetMapping("/{id}")
    public ColisDTO getById(@PathVariable Long id) { 
        return colisService.getById(id); 
    }

    @PostMapping
    public ColisDTO create(@RequestBody ColisDTO colis) {
        return colisService.create(colis);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { 
        colisService.delete(id); 
    }

    @GetMapping("/client/{clientId}")
    public List<ColisDTO> getByClient(@PathVariable Long clientId) {
        return colisService.getByClient(clientId);
    }

    @GetMapping("/livreur/{livreurId}")
    public List<ColisDTO> getByLivreur(@PathVariable Long livreurId) {
        return colisService.getByLivreur(livreurId);
    }
}
