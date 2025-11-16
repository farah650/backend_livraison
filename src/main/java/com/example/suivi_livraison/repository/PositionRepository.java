
package com.example.suivi_livraison.repository;


import com.example.suivi_livraison.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    // Historique des positions pour un livreur ordonné par dateHeure décroissante
    List<Position> findByLivreurIdOrderByDateHeureDesc(Long livreurId);
}
