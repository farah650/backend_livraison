package com.example.suivi_livraison.Services;
import com.example.suivi_livraison.DTO.ClientDTO;
import com.example.suivi_livraison.model.Client;
import com.example.suivi_livraison.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

        private ClientDTO toDTO(Client c) {
        ClientDTO dto = new ClientDTO();
        dto.setId(c.getId());
        dto.setNom(c.getNom());
        dto.setPrenom(c.getPrenom());
        dto.setEmail(c.getEmail());
        dto.setTelephone(c.getTelephone());
        dto.setAdresse(c.getAdresse());
        return dto;
    }
        private Client toEntity(ClientDTO dto) {
        Client c = new Client();
        c.setId(dto.getId()); // utile en update, sinon peut rester null en création
        c.setNom(dto.getNom());
        c.setPrenom(dto.getPrenom());
        c.setEmail(dto.getEmail());
        c.setTelephone(dto.getTelephone());
        c.setAdresse(dto.getAdresse());
        // motDePasse et role sont plutôt gérés via AuthService/register
        return c;
    }
    public List<ClientDTO> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ClientDTO getById(Long id) {
        return clientRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }
    public ClientDTO create(ClientDTO dto) {
        Client client = toEntity(dto);
        Client saved = clientRepository.save(client);
        return toDTO(saved);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
