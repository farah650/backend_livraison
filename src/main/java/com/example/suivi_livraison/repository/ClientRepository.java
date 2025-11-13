package com.example.suivi_livraison.repository;

import com.example.suivi_livraison.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // 🔹 Trouver un client par email (utile pour login ou vérification)
    Optional<Client> findByEmail(String email);

    // 🔹 Vérifier si un email existe déjà (inscription)
    boolean existsByEmail(String email);

    // 🔹 Trouver un client par nom et prénom (optionnel pour recherche)
    Optional<Client> findByNomAndPrenom(String nom, String prenom);
}
