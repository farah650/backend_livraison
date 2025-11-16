package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.model.Livraison;
import com.example.suivi_livraison.repository.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    // Obtenir les livraisons assignées à un livreur
    public List<Livraison> getAssignedDeliveries(Long idLivreur) {
        // Ex: Non livrées, ni annulées
        return livraisonRepository.findByLivreurIdAndStatutNot(idLivreur, "LIVREE");
    }

    // Historique des livraisons (toutes, ou uniquement LIVREE)
    public List<Livraison> getHistory(Long idLivreur) {
        
        return livraisonRepository.findByLivreurIdAndStatut(idLivreur, "LIVREE");
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
}
