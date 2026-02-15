package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.model.Notification;
import com.example.suivi_livraison.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByDestinataireOrderByDateEnvoiDesc(String destinataire);
    //List<Notification> findByUserIdOrderByDateEnvoiDesc(Long userId);
    //List<Notification> findByUserIdAndLueFalseOrderByDateEnvoiDesc(Long userId);
   
    
    // par utilisateur complet
    List<Notification> findByUtilisateurOrderByDateEnvoiDesc(Utilisateur utilisateur);

    List<Notification> findByUtilisateurAndLueFalseOrderByDateEnvoiDesc(Utilisateur utilisateur);
}