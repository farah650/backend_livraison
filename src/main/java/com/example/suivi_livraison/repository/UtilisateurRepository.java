package com.example.suivi_livraison.repository;
import com.example.suivi_livraison.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}
