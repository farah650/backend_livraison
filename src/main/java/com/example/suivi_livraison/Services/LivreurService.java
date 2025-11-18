package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.dto.PositionDTO;
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

    public Livreur findById(Long id) {
        return livreurRepo.findById(id).orElse(null);
    }

    public Livreur authenticate(String email, String password) {
        return livreurRepo.findByEmailAndPassword(email, password).orElse(null);
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
}