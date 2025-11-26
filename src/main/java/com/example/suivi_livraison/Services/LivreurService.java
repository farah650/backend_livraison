package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.DTO.LivreurDTO;
import com.example.suivi_livraison.DTO.PositionDTO;
import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.repository.LivreurRepository;
import com.example.suivi_livraison.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LivreurService {

    @Autowired private LivreurRepository livreurRepo;
    @Autowired private PositionRepository positionRepo;
    
    private LivreurDTO toDTO(Livreur l) {
        LivreurDTO dto = new LivreurDTO();
        dto.setId(l.getId());
        dto.setNom(l.getNom());
        dto.setPrenom(l.getPrenom());
        dto.setEmail(l.getEmail());
        dto.setTelephone(l.getTelephone());
        dto.setVehicleInfo(l.getVehicleInfo());
        dto.setDriverStatut(l.getDriverStatut());
        dto.setNote(l.getNote());
        dto.setActif(l.isActif());
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

    public Livreur create(Livreur livreur) { return livreurRepo.save(livreur); }

    public void delete(Long id) { livreurRepo.deleteById(id); }
}