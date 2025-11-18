package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByDestinataireOrderByDateEnvoiDesc(String destinataire);
    List<Notification> findByUserIdOrderByDateEnvoiDesc(Long userId);
    List<Notification> findByUserIdAndLueFalseOrderByDateEnvoiDesc(Long userId);
}