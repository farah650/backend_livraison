package com.example.suivi_livraison.repository;
import com.example.suivi_livraison.model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Long> {

    // Trouver un livreur par email (utile pour login si nécessaire)
    Optional<Livreur> findByEmail(String email);

    // Vérifier si un email existe déjà
    boolean existsByEmail(String email);

    // Liste des livreurs actifs
    List<Livreur> findByActifTrue();
}
