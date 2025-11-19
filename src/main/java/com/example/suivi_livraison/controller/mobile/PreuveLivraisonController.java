
package com.example.suivi_livraison.controller.mobile;

import com.example.suivi_livraison.Services.PreuveLivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mobile/preuve")
public class PreuveLivraisonController {

    @Autowired
    private PreuveLivraisonService preuveLivraisonService;

    /*@PostMapping
    public ResponseEntity<?> uploadPreuve(
        @RequestParam Long idLivraison,
        @RequestParam("file") MultipartFile file // pour une image ou doc
    ) {
        boolean success = preuveLivraisonService.savePreuve(idLivraison, file);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Echec upload");
        }
    }*/
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPreuve(
        @RequestParam Long idLivraison,
        @RequestParam("file") MultipartFile file
    ) {
        boolean success = preuveLivraisonService.savePreuve(idLivraison, file);
        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Echec upload");
        }
    }

    @GetMapping("/{idLivraison}")
    public ResponseEntity<byte[]> getPreuve(@PathVariable Long idLivraison) {
        byte[] preuve = preuveLivraisonService.getPreuveForLivraison(idLivraison);
        if (preuve != null) {
            return ResponseEntity.ok(preuve);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
