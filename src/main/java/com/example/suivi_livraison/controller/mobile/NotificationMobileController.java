package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.Services.NotificationService;
import com.example.suivi_livraison.DTO.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mobile/notifications")
public class NotificationMobileController {

    @Autowired
    private NotificationService service;

    // GET toutes les notifications
    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET notifications par destinataire
    @GetMapping("/destinataire/{dest}")
    public ResponseEntity<List<NotificationDTO>> getByDestinataire(@PathVariable String dest) {
        return ResponseEntity.ok(service.getByDestinataire(dest));
    }

    // GET notifications par userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getByUserId(userId));
    }

    // GET notifications non lues
    @GetMapping("/non-lues/{userId}")
    public ResponseEntity<List<NotificationDTO>> getNonLues(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getNonLues(userId));
    }

    // PUT pour marquer une notification comme lue
    @PutMapping("/{id}/lire")
    public ResponseEntity<NotificationDTO> markRead(@PathVariable Long id) {
        return ResponseEntity.ok(service.markAsRead(id));
    }

    // POST pour créer une notification
    @PostMapping
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    // DELETE pour supprimer une notification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
