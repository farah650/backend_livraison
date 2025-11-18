package com.example.suivi_livraison.dto;

import java.time.LocalDateTime;

public record EvaluationDTO(
        Long id,
        Integer note,
        String commentaire,
        LocalDateTime dateEvaluation,
        String nomClient // "Utilisateur anonyme" si null
) {}
