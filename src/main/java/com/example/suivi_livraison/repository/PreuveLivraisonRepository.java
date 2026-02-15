package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.model.PreuveLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreuveLivraisonRepository extends JpaRepository<PreuveLivraison, Long> {
    // Pour trouver la preuve associée à une livraison
    Optional<PreuveLivraison> findByLivraisonId(Long livraisonId);
}
