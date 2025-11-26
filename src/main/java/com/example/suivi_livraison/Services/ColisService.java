package com.example.suivi_livraison.Services;
import com.example.suivi_livraison.DTO.ColisDTO;
import com.example.suivi_livraison.model.Colis;
import com.example.suivi_livraison.repository.ClientRepository;
import com.example.suivi_livraison.repository.ColisRepository;
import com.example.suivi_livraison.repository.LivreurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColisService {

    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private LivreurRepository livreurRepository;

    private ColisDTO toDTO(Colis c) {
        ColisDTO dto = new ColisDTO();
        dto.setId(c.getId());
        dto.setCodeBarres(c.getCodeBarres());
        dto.setStatut(c.getStatut());
        if (c.getClient() != null) dto.setClientId(c.getClient().getId());
        if (c.getLivreur() != null) dto.setLivreurId(c.getLivreur().getId());
        return dto;
    }

    private Colis toEntity(ColisDTO dto) {
        Colis c = new Colis();
        c.setId(dto.getId());
        c.setCodeBarres(dto.getCodeBarres());
        c.setStatut(dto.getStatut());

        if (dto.getClientId() != null) {
            c.setClient(clientRepository.findById(dto.getClientId()).orElse(null));
        }
        if (dto.getLivreurId() != null) {
            c.setLivreur(livreurRepository.findById(dto.getLivreurId()).orElse(null));
        }
        return c;
    }

    public List<ColisDTO> getAll() {
        return colisRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ColisDTO getById(Long id) {
        return colisRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    public ColisDTO create(ColisDTO dto) {
        Colis entity = toEntity(dto);
        Colis saved = colisRepository.save(entity);
        return toDTO(saved);
    }

    public void delete(Long id) {
        colisRepository.deleteById(id);
    }

    public List<ColisDTO> getByClient(Long clientId) {
        return colisRepository.findByClientId(clientId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ColisDTO> getByLivreur(Long livreurId) {
        return colisRepository.findByLivreurId(livreurId)
                .stream()
                .map(this::toDTO)
                .toList();
    }
}

