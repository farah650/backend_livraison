// src/main/java/com/example/suivi_livraison/controller/mobile/EvaluationMobileController.java

package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.dto.EvaluationDTO;
import com.example.suivi_livraison.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/mobile/evaluation")
@CrossOrigin(origins = "*")
public class EvaluationMobileController {

    @Autowired
    private EvaluationRepository evaluationRepository;

    // SEULEMENT LE GET pour l'app mobile : profil livreur avec moyenne + liste
    @GetMapping("/profil/{idLivreur}")
    public ResponseEntity<Map<String, Object>> profil(@PathVariable Long idLivreur) {
        Double moyenne = evaluationRepository.getMoyenneByLivreur(idLivreur);
        long nombre = evaluationRepository.getNombreEvaluations(idLivreur);
        List<EvaluationDTO> evals = evaluationRepository.findByIdLivreurWithClientName(idLivreur);

        Map<String, Object> response = new HashMap<>();
        response.put("moyenne", String.format("%.1f", moyenne != null ? moyenne : 0.0));
        response.put("nombreEvaluations", nombre);
        response.put("evaluations", evals);

        return ResponseEntity.ok(response);
    }
}