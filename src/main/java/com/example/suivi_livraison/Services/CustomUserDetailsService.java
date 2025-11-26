package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.model.Utilisateur;
import com.example.suivi_livraison.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email.trim().toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("Aucun utilisateur trouvé : " + email);
        }

        String role = user.getRole(); // "CLIENT", "LIVREUR"
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));

        return new User(
                user.getEmail(),
                user.getMotDePasse(),
                authorities
        );
    }
}
