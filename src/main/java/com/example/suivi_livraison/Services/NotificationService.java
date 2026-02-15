package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.DTO.NotificationDTO;
import com.example.suivi_livraison.model.Notification;
import com.example.suivi_livraison.model.Utilisateur;
import com.example.suivi_livraison.repository.NotificationRepository;
import com.example.suivi_livraison.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<NotificationDTO> getAll() {
        return repo.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getById(Long id) {
        Notification n = repo.findById(id).orElse(null);
        return n != null ? toDTO(n) : null;
    }

    public List<NotificationDTO> getByDestinataire(String destinataire) {
        return repo.findByDestinataireOrderByDateEnvoiDesc(destinataire)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // si ton repo a bien une méthode basée sur l'utilisateur
    public List<NotificationDTO> getByUserId(Long userId) {
        Utilisateur u = utilisateurRepository.findById(userId).orElse(null);
        if (u == null) return List.of();
        return repo.findByUtilisateurOrderByDateEnvoiDesc(u)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<NotificationDTO> getNonLues(Long userId) {
        Utilisateur u = utilisateurRepository.findById(userId).orElse(null);
        if (u == null) return List.of();
        return repo.findByUtilisateurAndLueFalseOrderByDateEnvoiDesc(u)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO markAsRead(Long id) {
        Notification n = repo.findById(id).orElseThrow();
        n.setLue(true);
        return toDTO(repo.save(n));
    }

    public NotificationDTO create(NotificationDTO dto) {
        Notification n = new Notification();
        n.setMessage(dto.getMessage());
        n.setType(dto.getType());
        n.setDestinataire(dto.getDestinataire());

        if (dto.getUserId() != null) {
            Utilisateur u = utilisateurRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
            n.setUtilisateur(u);
        }

        return toDTO(repo.save(n));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    private NotificationDTO toDTO(Notification n) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(n.getId());
        dto.setDateEnvoi(n.getDateEnvoi());
        dto.setDestinataire(n.getDestinataire());
        dto.setMessage(n.getMessage());
        dto.setLue(n.isLue());
        dto.setType(n.getType());
        dto.setUserId(
                n.getUtilisateur() != null ? n.getUtilisateur().getId() : null
        );
        return dto;
    }
}
