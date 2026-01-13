package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.DTO.LivreurDTO;
import com.example.suivi_livraison.DTO.PositionDTO;
import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.repository.EvaluationRepository;
import com.example.suivi_livraison.repository.LivreurRepository;
import com.example.suivi_livraison.repository.PositionRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class LivreurService {

    @Autowired private LivreurRepository livreurRepo;
    @Autowired private PositionRepository positionRepo;
    @Autowired private EvaluationRepository evaluationRepo;
    @Autowired private PasswordEncoder passwordEncoder; 
    public LivreurDTO toDTO(Livreur l) {
        LivreurDTO dto = new LivreurDTO();
        dto.setId(l.getId());
        dto.setNom(l.getNom());
        dto.setPrenom(l.getPrenom());
        dto.setEmail(l.getEmail());
        dto.setTelephone(l.getTelephone());
        dto.setVehicleInfo(l.getVehicleInfo());
        dto.setTypeVehicule(l.getTypeVehicule());
        dto.setDriverStatut(l.getDriverStatut());
        dto.setNote(l.getNote());
        dto.setActif(l.isActif());
        Double avg = evaluationRepo.getMoyenneByLivreur(l.getId());
        dto.setNote(avg != null ? avg : 0.0);

        return dto;
    }
    public LivreurDTO findById(Long id) {
        return livreurRepo.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

   

    public Position addPosition(Long livreurId, double latitude, double longitude) {
        Livreur livreur = livreurRepo.findById(livreurId).orElseThrow();
        Position pos = new Position();
        pos.setLivreur(livreur);
        pos.setLatitude(latitude);
        pos.setLongitude(longitude);
        return positionRepo.save(pos);
    }

    public List<PositionDTO> getPositions(Long livreurId) {
        return positionRepo.findByLivreurIdOrderByDateHeureDesc(livreurId)
                .stream()
                .map(p -> {
                    PositionDTO dto = new PositionDTO();
                    dto.setLatitude(p.getLatitude());
                    dto.setLongitude(p.getLongitude());
                    dto.setDateHeure(p.getDateHeure());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<LivreurDTO> getAll() {
        return livreurRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    public LivreurDTO getById(Long id) { return livreurRepo.findById(id)
                .map(this::toDTO)
                .orElse(null); }

    public Livreur create(Livreur livreur) {
    // ✅ HASH mot de passe AVANT save
    if (livreur.getMotDePasse() != null && !livreur.getMotDePasse().startsWith("$2a$")) {
        livreur.setMotDePasse(passwordEncoder.encode(livreur.getMotDePasse()));
    }
    return livreurRepo.save(livreur);
}
     public Livreur updateFromDTO(LivreurDTO dto) {
    Livreur existing = livreurRepo.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Livreur non trouvé"));
    
    existing.setNom(dto.getNom());
    existing.setPrenom(dto.getPrenom());
    existing.setEmail(dto.getEmail());
    existing.setTelephone(dto.getTelephone());
    existing.setVehicleInfo(dto.getVehicleInfo());
    existing.setTypeVehicule(dto.getTypeVehicule());
    existing.setDriverStatut(dto.getDriverStatut());
    existing.setNote(dto.getNote());
    existing.setActif(dto.isActif());
    existing.setDateModification(new Date());
    
    return livreurRepo.save(existing);
}

    public void delete(Long id) { livreurRepo.deleteById(id); }
}