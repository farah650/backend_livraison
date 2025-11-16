package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.model.Livraison;
import com.example.suivi_livraison.model.PreuveLivraison;
import com.example.suivi_livraison.repository.LivraisonRepository;
import com.example.suivi_livraison.repository.PreuveLivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PreuveLivraisonService {

    @Autowired
    private PreuveLivraisonRepository preuveLivraisonRepository;
    @Autowired
    private LivraisonRepository livraisonRepository;

    public boolean savePreuve(Long idLivraison, MultipartFile file) {
        try {
            Optional<Livraison> optLivraison = livraisonRepository.findById(idLivraison);
            if (optLivraison.isPresent()) {
                PreuveLivraison preuve = new PreuveLivraison();
                preuve.setImage(file.getBytes());
                preuve.setType(file.getContentType());
                preuve.setLivraison(optLivraison.get());
                preuveLivraisonRepository.save(preuve);
                return true;
            }
        } catch (IOException e) {
            // Log ou gestion d’erreur
        }
        return false;
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
