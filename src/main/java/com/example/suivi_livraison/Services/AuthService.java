package com.example.suivi_livraison.Services;


import com.example.suivi_livraison.DTO.AuthResponse;
import com.example.suivi_livraison.DTO.RegisterRequest;
import com.example.suivi_livraison.model.Utilisateur;
import com.example.suivi_livraison.repository.UtilisateurRepository;
import com.example.suivi_livraison.config.JwtService;
import com.example.suivi_livraison.model.Client;
import com.example.suivi_livraison.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
@Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String login(String email, String motDePasse) {
        if (email == null || motDePasse == null) return null;

        Utilisateur user = utilisateurRepository.findByEmail(email.trim().toLowerCase());
        if (user == null) return null;

        if (passwordEncoder.matches(motDePasse, user.getMotDePasse())) {
            return jwtService.generateToken(user.getEmail(), user.getRole());
        }
        return null;
    }
    public AuthResponse loginResponse(String email, String motDePasse) {
    String token = login(email, motDePasse);
    if (token == null) {
        return null;
    }

    Utilisateur user = utilisateurRepository.findByEmail(email.trim().toLowerCase());
    AuthResponse resp = new AuthResponse();
    resp.setToken(token);
    resp.setRole(user.getRole());
    resp.setUserId(user.getId());
    resp.setFirstName(user.getNom());
    resp.setLastName(user.getPrenom());
    return resp;
}


    // RESTE DU CODE INCHANGÉ...
    @Transactional
public void register(RegisterRequest u) {

    if (utilisateurRepository.findByEmail(u.getEmail().trim().toLowerCase()) != null) {
        throw new RuntimeException("Email déjà utilisé");
    }

    Utilisateur user;

    if ("CLIENT".equalsIgnoreCase(u.getRole())) {
        Client client = new Client();
        client.setAdresse(u.getAdresse());
        user = client;
    } else {
        user = new Utilisateur();
    }

    user.setNom(u.getNom());
    user.setPrenom(u.getPrenom());
    user.setEmail(u.getEmail().trim().toLowerCase());
    user.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));
    user.setRole(u.getRole());
    user.setTelephone(u.getTelephone());

    utilisateurRepository.save(user);
}


    public void registerBatch(List<RegisterRequest> users) {
    for (RegisterRequest u : users) {
        if (utilisateurRepository.findByEmail(u.getEmail().trim().toLowerCase()) != null) continue;

        Utilisateur user = new Utilisateur();
        user.setNom(u.getNom());
        user.setPrenom(u.getPrenom());
        user.setEmail(u.getEmail().trim().toLowerCase());
        user.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));
        user.setRole(u.getRole() != null ? u.getRole() : "USER");
        user.setTelephone(u.getTelephone()); // facultatif

        utilisateurRepository.save(user);
    }
}

    }
