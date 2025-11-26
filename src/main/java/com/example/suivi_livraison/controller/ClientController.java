package com.example.suivi_livraison.controller;
import com.example.suivi_livraison.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.suivi_livraison.Services.ClientService;
import com.example.suivi_livraison.DTO.ClientDTO;
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public  List<ClientDTO> getAll() { return clientService.getAll(); }

    @GetMapping("/{id}")
    public ClientDTO getById(@PathVariable Long id) { return clientService.getById(id); }

    @PostMapping
    public ClientDTO create(@RequestBody ClientDTO dto) {
        return clientService.create(dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { clientService.delete(id); }
}
