package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.model.Notification;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.repository.LivreurRepository;
import com.example.suivi_livraison.repository.NotificationRepository;
import com.example.suivi_livraison.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LivreurService {

    @Autowired
    private LivreurRepository livreurRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private PositionRepository positionRepository;

    // Authentifier un livreur
   public Optional<Livreur> authenticate(String email, String password) {
    Optional<Livreur> livreurOpt = livreurRepository.findByEmail(email);
    if (livreurOpt.isPresent()) {
    Livreur livreur = livreurOpt.get();
    System.out.println("Mail/API: " + email + ", DB: " + livreur.getEmail());
    System.out.println("PW/API: " + password + ", DB: " + livreur.getPassword());

    // ---> Ajoute-ci dessous :
    System.out.println("Egalité brute: " + livreur.getPassword().equals(password));
    System.out.println("Egalité trim: " + livreur.getPassword().trim().equals(password.trim()));
    System.out.println("Egalité ignoreCase: " + livreur.getPassword().equalsIgnoreCase(password));

    // Et ensuite la condition existante :
    if (livreur.getPassword() != null && livreur.getPassword().trim().equals(password.trim())) {
    return livreurOpt;
}

}

    return Optional.empty();
}

    // Récupérer un livreur par id
    public Optional<Livreur> findById(Long id) {
        return livreurRepository.findById(id);
    }

    // Ajouter une nouvelle position
    public Position addPosition(Long livreurId, double latitude, double longitude) {
        Optional<Livreur> livreurOpt = livreurRepository.findById(livreurId);
        if (livreurOpt.isPresent()) {
            Position pos = new Position();
            pos.setLivreur(livreurOpt.get());
            pos.setLatitude(latitude);
            pos.setLongitude(longitude);
            pos.setDateHeure(LocalDateTime.now());
            return positionRepository.save(pos);
        }
        return null;
    }

    // Historique des positions pour un livreur
    public List<Position> getPositions(Long livreurId) {
        return positionRepository.findByLivreurIdOrderByDateHeureDesc(livreurId);
    }

    // Notifications destinées au livreur
    public List<Notification> getNotifications(Long idLivreur) {
        return notificationRepository.findByDestinataire("Livreur");
    }
}
