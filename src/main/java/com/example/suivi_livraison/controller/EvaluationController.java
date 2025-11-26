// src/main/java/com/example/suivi_livraison/controller/EvaluationController.java

package com.example.suivi_livraison.controller;

import com.example.suivi_livraison.DTO.EvaluationDTO;
import com.example.suivi_livraison.entity.Evaluation;
import com.example.suivi_livraison.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation")
@CrossOrigin(origins = "*")
public class EvaluationController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    // GET toutes les évaluations
    @GetMapping
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationRepository.findAllWithClientName();
    }

    // GET une évaluation par ID
    @GetMapping("/{id}")
    public ResponseEntity<EvaluationDTO> getEvaluationById(@PathVariable Long id) {
        return evaluationRepository.findByIdWithClientName(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST créer une évaluation
    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        if (evaluation.getNote() == null || evaluation.getNote() < 1 || evaluation.getNote() > 5) {
            return ResponseEntity.badRequest().build();
        }
        Evaluation saved = evaluationRepository.save(evaluation);
        return ResponseEntity.ok(saved);
    }

    // PUT modifier une évaluation → CORRIGÉ
    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(
            @PathVariable Long id,
            @RequestBody Evaluation evaluationDetails) {

        if (!evaluationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Evaluation existing = evaluationRepository.findById(id).get();

        if (evaluationDetails.getNote() != null) {
            if (evaluationDetails.getNote() < 1 || evaluationDetails.getNote() > 5) {
                return ResponseEntity.badRequest().build();
            }
            existing.setNote(evaluationDetails.getNote());
        }
        if (evaluationDetails.getCommentaire() != null) {
            existing.setCommentaire(evaluationDetails.getCommentaire());
        }
        if (evaluationDetails.getIdLivreur() != null) {
            existing.setIdLivreur(evaluationDetails.getIdLivreur());
        }
        if (evaluationDetails.getIdClient() != null) {
            existing.setIdClient(evaluationDetails.getIdClient());
        }

        Evaluation updated = evaluationRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    // DELETE supprimer une évaluation → CORRIGÉ
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        if (!evaluationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        evaluationRepository.deleteById(id);
        return ResponseEntity.noContent().build();   // 204 No Content (meilleure pratique pour DELETE)
    }
}