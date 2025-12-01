package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.model.Livraison;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.model.PreuveLivraison;
import com.example.suivi_livraison.repository.LivraisonRepository;
import com.example.suivi_livraison.repository.PositionRepository;
import com.example.suivi_livraison.repository.PreuveLivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PreuveLivraisonService {

    @Autowired
    private PreuveLivraisonRepository preuveLivraisonRepository;

    @Autowired
    private LivraisonRepository livraisonRepository;

    @Autowired
    private PositionRepository positionRepository;

    public boolean savePreuve(Long idLivraison, MultipartFile file) {
        try {
            Optional<Livraison> optLivraison = livraisonRepository.findById(idLivraison);
            if (optLivraison.isEmpty()) {
                return false;
            }

            Livraison livraison = optLivraison.get();

            PreuveLivraison preuve = new PreuveLivraison();
            preuve.setImage(file.getBytes());
            preuve.setType(file.getContentType());
            preuve.setLivraison(livraison);

            // Récupérer la dernière position du livreur
            if (livraison.getLivreur() != null) {
                Long livreurId = livraison.getLivreur().getId();

                List<Position> positions =
                        positionRepository.findByLivreurIdOrderByDateHeureDesc(livreurId);

                if (!positions.isEmpty()) {
                    Position dernierePosition = positions.get(0);

                    // Lier la position à la preuve
                    preuve.setPosition(dernierePosition);

                    // Recopier latitude/longitude dans la table preuve_livraison
                    preuve.setLatitude(dernierePosition.getLatitude());
                    preuve.setLongitude(dernierePosition.getLongitude());
                }
            }

            preuveLivraisonRepository.save(preuve);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public byte[] getPreuveForLivraison(Long idLivraison) {
        Optional<PreuveLivraison> preuve = preuveLivraisonRepository.findByLivraisonId(idLivraison);
        return preuve.map(PreuveLivraison::getImage).orElse(null);
    }

    public String getPreuveType(Long idLivraison) {
        Optional<PreuveLivraison> preuve = preuveLivraisonRepository.findByLivraisonId(idLivraison);
        return preuve.map(PreuveLivraison::getType).orElse("application/octet-stream");
    }
}
