package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.dto.EvaluationDTO;
import com.example.suivi_livraison.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query("""
            SELECT new com.example.suivi_livraison.dto.EvaluationDTO(
                e.id, e.note, e.commentaire, e.dateEvaluation,
                COALESCE(c.nom, 'Utilisateur anonyme')
            )
            FROM Evaluation e
            LEFT JOIN Client c ON e.idClient = c.id
            ORDER BY e.dateEvaluation DESC
            """)
    List<EvaluationDTO> findAllWithClientName();

    @Query("""
            SELECT new com.example.suivi_livraison.dto.EvaluationDTO(
                e.id, e.note, e.commentaire, e.dateEvaluation,
                COALESCE(c.nom, 'Utilisateur anonyme')
            )
            FROM Evaluation e
            LEFT JOIN Client c ON e.idClient = c.id
            WHERE e.id = :id
            """)
    Optional<EvaluationDTO> findByIdWithClientName(Long id);

    @Query("""
            SELECT new com.example.suivi_livraison.dto.EvaluationDTO(
                e.id, e.note, e.commentaire, e.dateEvaluation,
                COALESCE(c.nom, 'Utilisateur anonyme')
            )
            FROM Evaluation e
            LEFT JOIN Client c ON e.idClient = c.id
            WHERE e.idLivreur = :idLivreur
            ORDER BY e.dateEvaluation DESC
            """)
    List<EvaluationDTO> findByIdLivreurWithClientName(Long idLivreur);

    @Query("SELECT AVG(e.note) FROM Evaluation e WHERE e.idLivreur = :idLivreur")
    Double getMoyenneByLivreur(Long idLivreur);

    @Query("SELECT COUNT(e) FROM Evaluation e WHERE e.idLivreur = :idLivreur")
    long getNombreEvaluations(Long idLivreur);
}
