package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findByLivreurIdOrderByDateHeureDesc(Long livreurId);
}