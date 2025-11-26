package com.example.suivi_livraison.controller;

import com.example.suivi_livraison.Services.NotificationService;
import com.example.suivi_livraison.DTO.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<NotificationDTO> getAll() {
        return notificationService.getAll();
    }

    @GetMapping("/{id}")
    public NotificationDTO getById(@PathVariable Long id) {
        return notificationService.getById(id);
    }

    @PostMapping
    public NotificationDTO create(@RequestBody NotificationDTO dto) {
        return notificationService.create(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        notificationService.delete(id);
    }

    @GetMapping("/destinataire/{dest}")
    public List<NotificationDTO> getByDestinataire(@PathVariable String dest) {
        return notificationService.getByDestinataire(dest);
    }
}
