package com.example.suivi_livraison.repository;
import com.example.suivi_livraison.model.Colis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {

    // 🔹 Liste des colis par client
    List<Colis> findByClientId(Long clientId);

    // 🔹 Liste des colis par livreur
    List<Colis> findByLivreurId(Long livreurId);

    // 🔹 Liste des colis par statut
    List<Colis> findByStatut(String statut);
     
}
