package com.example.suivi_livraison.Services;
import com.example.suivi_livraison.DTO.ClientDTO;
import com.example.suivi_livraison.model.Client;
import com.example.suivi_livraison.repository.ClientRepository;
import java.util.Date;
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
        dto.setDateCreation(c.getDateCreation()); 
       
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
        Client client;
    // ✅ SI ID existe → UPDATE, SINON CREATE
    if (dto.getId() != null && dto.getId() > 0) {
        // UPDATE
        client = clientRepository.findById(dto.getId())
            .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setAdresse(dto.getAdresse());
        client.setDateModification(new Date());
    } else {
        // CREATE
        client = toEntity(dto);
    }
    
    Client saved = clientRepository.save(client);
    return toDTO(saved);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
