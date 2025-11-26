package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.model.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LivreurRepository extends JpaRepository<Livreur, Long> {
    Optional<Livreur> findByEmail(String email);
}