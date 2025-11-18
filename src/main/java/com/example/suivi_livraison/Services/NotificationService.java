package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.dto.NotificationDTO;
import com.example.suivi_livraison.model.Notification;
import com.example.suivi_livraison.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repo;

    public List<NotificationDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<NotificationDTO> getByDestinataire(String destinataire) {
        return repo.findByDestinataireOrderByDateEnvoiDesc(destinataire)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<NotificationDTO> getByUserId(Long userId) {
        return repo.findByUserIdOrderByDateEnvoiDesc(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<NotificationDTO> getNonLues(Long userId) {
        return repo.findByUserIdAndLueFalseOrderByDateEnvoiDesc(userId)
                .stream().map(this::toDTO).collect(Collectors.toList());
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
        n.setUserId(dto.getUserId());
        return toDTO(repo.save(n));
    }

    private NotificationDTO toDTO(Notification n) {
        NotificationDTO dto = new NotificationDTO();
        dto.setId(n.getId());
        dto.setDateEnvoi(n.getDateEnvoi());
        dto.setDestinataire(n.getDestinataire());
        dto.setMessage(n.getMessage());
        dto.setLue(n.isLue());
        dto.setType(n.getType());
        dto.setUserId(n.getUserId());
        return dto;
    }
    public void delete(Long id) {
    repo.deleteById(id);
}

}
