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
    @PutMapping("/{id}")
    public LivreurDTO update(@PathVariable Long id, @RequestBody LivreurDTO livreurDTO) {
        if (!id.equals(livreurDTO.getId())) {
            throw new RuntimeException("ID mismatch");
        }
        Livreur updated = livreurService.updateFromDTO(livreurDTO);
        return livreurService.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        livreurService.delete(id);
    }
}
