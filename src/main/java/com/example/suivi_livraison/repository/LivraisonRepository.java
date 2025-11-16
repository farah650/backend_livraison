package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.model.Livraison;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {
    // Liste des livraisons assignées à un livreur, qui ne sont pas livrées (EN_COURS, etc.)
    List<Livraison> findByLivreurIdAndStatutNot(Long idLivreur, String statut);

    // Historique de toutes les livraisons (optionnellement, filtre par statut)
    List<Livraison> findByLivreurId(Long idLivreur);
    // Historique (ex : tout sauf les en cours, livrées, annulées, etc.)
    List<Livraison> findByLivreurIdAndStatut(Long idLivreur, String statut);
    
    Optional<Livraison> findByIdAndLivreurId(Long idLivraison, Long idLivreur);

    
}
