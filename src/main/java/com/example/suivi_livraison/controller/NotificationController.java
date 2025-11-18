package com.example.suivi_livraison.controller;

import com.example.suivi_livraison.model.Notification;
import com.example.suivi_livraison.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/destinataire/{dest}")
    public ResponseEntity<List<Notification>> getByDestinataire(@PathVariable String dest) {
        List<Notification> notifs = notificationRepository.findByDestinataireOrderByDateEnvoiDesc(dest);
        return ResponseEntity.ok(notifs);
    }
}