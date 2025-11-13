package com.example.suivi_livraison.repository;
import com.example.suivi_livraison.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // 🔹 Notifications pour un destinataire spécifique
    List<Notification> findByDestinataire(String destinataire);

    // 🔹 Notifications récentes
    List<Notification> findAllByOrderByDateEnvoiDesc();
}
