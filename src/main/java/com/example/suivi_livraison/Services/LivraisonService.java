package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.DTO.ClientDTO;
import com.example.suivi_livraison.DTO.LivraisonDTO;
import com.example.suivi_livraison.DTO.LivreurDTO;
import com.example.suivi_livraison.DTO.TrackingDTO;
import com.example.suivi_livraison.model.Client;
import com.example.suivi_livraison.model.Livraison;
import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.repository.ClientRepository;
import com.example.suivi_livraison.repository.LivraisonRepository;
import com.example.suivi_livraison.repository.LivreurRepository;
import com.example.suivi_livraison.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;
        @Autowired
    private PositionRepository positionRepository;
        @Autowired
private ClientRepository clientRepository;

@Autowired
private LivreurRepository livreurRepo;
    private LivraisonDTO toDTO(Livraison livraison) {
    LivraisonDTO dto = new LivraisonDTO();
    dto.setId(livraison.getId());
    dto.setAdresse(livraison.getAdresse());
    dto.setDateLivraison(livraison.getDateLivraison());
    dto.setStatut(livraison.getStatut());
    dto.setCodeBarre(livraison.getCodeBarre());
    
    // Mapper livreur
    if (livraison.getLivreur() != null) {
        LivreurDTO livreurDTO = new LivreurDTO();
        livreurDTO.setId(livraison.getLivreur().getId());
        livreurDTO.setNom(livraison.getLivreur().getNom());
        livreurDTO.setPrenom(livraison.getLivreur().getPrenom());
        livreurDTO.setEmail(livraison.getLivreur().getEmail());
        livreurDTO.setTelephone(livraison.getLivreur().getTelephone());
        livreurDTO.setVehicleInfo(livraison.getLivreur().getVehicleInfo());
        livreurDTO.setDriverStatut(livraison.getLivreur().getDriverStatut());
        livreurDTO.setNote(livraison.getLivreur().getNote());
        livreurDTO.setActif(livraison.getLivreur().isActif());
        dto.setLivreur(livreurDTO);
    }
   
    // Mapper client
    if (livraison.getClient() != null) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(livraison.getClient().getId());
        clientDTO.setNom(livraison.getClient().getNom());
        clientDTO.setPrenom(livraison.getClient().getPrenom());
        clientDTO.setEmail(livraison.getClient().getEmail());
        clientDTO.setTelephone(livraison.getClient().getTelephone());
        clientDTO.setAdresse(livraison.getClient().getAdresse());
        dto.setClient(clientDTO);
    }
    
    return dto;
}

    // Obtenir les livraisons assignées à un livreur
public List<LivraisonDTO> getAssignedDeliveries(Long idLivreur) {
    return livraisonRepository.findByLivreurIdAndStatutNot(idLivreur, "LIVREE")
            .stream()
            .map(this::toDTO)
            .toList();
}

// Historique des livraisons livrées
public List<LivraisonDTO> getHistory(Long idLivreur) {
    return livraisonRepository.findByLivreurIdAndStatut(idLivreur, "LIVREE")
            .stream()
            .map(this::toDTO)
            .toList();
}

    // Changer le statut d’une livraison
    public boolean updateStatut(Long idLivraison, String statut) {
        Optional<Livraison> optLivraison = livraisonRepository.findById(idLivraison);
        if (optLivraison.isPresent()) {
            Livraison l = optLivraison.get();
            l.setStatut(statut);
            livraisonRepository.save(l);
            return true;
        }
        return false;
    }
    public boolean validerScan(Long idLivraison, Long idLivreur, String codeBarreScanne) {
        Optional<Livraison> opt = livraisonRepository.findByIdAndLivreurId(idLivraison, idLivreur);
        if(opt.isPresent()) {
            Livraison l = opt.get();
            return l.getCodeBarre() != null && l.getCodeBarre().equals(codeBarreScanne);
        }
        return false;
    }
    /*farah*/
    public List<LivraisonDTO> getAll() { 
    return livraisonRepository.findAll()
            .stream()
            .map(this::toDTO)
            .toList();
}


    public LivraisonDTO getById(Long id) { 
    Livraison livraison = livraisonRepository.findById(id).orElse(null);
    return livraison != null ? toDTO(livraison) : null;
}
    public LivraisonDTO create(LivraisonDTO dto) { 
    // Mapper DTO -> Entité
    Livraison livraison = new Livraison();
    livraison.setAdresse(dto.getAdresse());
    livraison.setDateLivraison(dto.getDateLivraison());
    livraison.setStatut(dto.getStatut());
    livraison.setCodeBarre(dto.getCodeBarre());
    
    // Lier livreur et client si les IDs sont fournis
    if (dto.getLivreur() != null && dto.getLivreur().getId() != null) {
        Livreur livreur = new Livreur();
        livreur.setId(dto.getLivreur().getId());
        livraison.setLivreur(livreur);
    }
    
    if (dto.getClient() != null && dto.getClient().getId() != null) {
        Client client = new Client();
        client.setId(dto.getClient().getId());
        livraison.setClient(client);
    }
    
    Livraison saved = livraisonRepository.save(livraison);
    return toDTO(saved);
}
     public Livraison update(Long id, LivraisonDTO dto) {
  Livraison livraison = livraisonRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Livraison non trouvée"));
  
  if (dto.getCodeBarre() != null) {
    livraison.setCodeBarre(dto.getCodeBarre());
  }
  if (dto.getAdresse() != null) {
    livraison.setAdresse(dto.getAdresse());
  }
  
  // ✅ Mapper DTO → Entity
  if (dto.getClient() != null && dto.getClient().getId() != null) {
    Client clientEntity = clientRepository.findById(dto.getClient().getId()).orElse(null);
    if (clientEntity != null) {
      livraison.setClient(clientEntity);
    }
  }
  
  if (dto.getLivreur() != null && dto.getLivreur().getId() != null) {
    Livreur livreurEntity = livreurRepo.findById(dto.getLivreur().getId()).orElse(null);
    if (livreurEntity != null) {
      livraison.setLivreur(livreurEntity);
    }
  }
  
  if (dto.getStatut() != null) {
    livraison.setStatut(dto.getStatut());
  }
  
  return livraisonRepository.save(livraison);
}


public void delete(Long id) { livraisonRepository.deleteById(id); }

public List<LivraisonDTO> getByClient(Long id) {
    return livraisonRepository.findByClientId(id)
            .stream()
            .map(this::toDTO)
            .toList();
}

public List<LivraisonDTO> getByLivreur(Long id) {
    return livraisonRepository.findByLivreurId(id)
            .stream()
            .map(this::toDTO)
            .toList();
}
    public TrackingDTO getTrackingForClient(Long livraisonId) {
        // 1. Récupérer la livraison
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(() -> new RuntimeException("Livraison introuvable"));

        Livreur livreur = livraison.getLivreur();
        if (livreur == null) {
            throw new RuntimeException("Aucun livreur assigné à cette livraison");
        }

        // 2. Récupérer la dernière position du livreur
        List<Position> positions = positionRepository.findByLivreurIdOrderByDateHeureDesc(livreur.getId());
        Position lastPosition = positions.isEmpty() ? null : positions.get(0);

        double livreurLat = 0;
        double livreurLng = 0;
        if (lastPosition != null) {
            livreurLat = lastPosition.getLatitude();
            livreurLng = lastPosition.getLongitude();
        }

        // 3. Retourner DTO avec la position du livreur + adresse du client
        return new TrackingDTO(livreurLat, livreurLng, livraison.getAdresse());
    }

}
