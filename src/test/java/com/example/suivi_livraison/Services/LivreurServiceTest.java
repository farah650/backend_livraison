package com.example.suivi_livraison.Services;

import com.example.suivi_livraison.DTO.LivreurPositionDTO;
import com.example.suivi_livraison.model.Livreur;
import com.example.suivi_livraison.model.Position;
import com.example.suivi_livraison.repository.LivreurRepository;
import com.example.suivi_livraison.repository.PositionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class LivreurServiceTest {

    @Mock
    private LivreurRepository livreurRepo;
    @Mock
    private PositionRepository positionRepo;
    @InjectMocks
    private LivreurService service;

    @Test
    void getAllLatestPositions_returnsLatestPerLivreur() {
        MockitoAnnotations.openMocks(this);

        Livreur l1 = new Livreur();
        l1.setId(1L);
        l1.setNom("Dupont");
        l1.setPrenom("Jean");
        Livreur l2 = new Livreur();
        l2.setId(2L);
        l2.setNom("Martin");
        l2.setPrenom("Claire");

        Position p1 = new Position();
        p1.setLatitude(10.0);
        p1.setLongitude(20.0);
        p1.setDateHeure(LocalDateTime.now());

        when(livreurRepo.findAll()).thenReturn(Arrays.asList(l1, l2));
        when(positionRepo.findByLivreurIdOrderByDateHeureDesc(1L)).thenReturn(Arrays.asList(p1));
        when(positionRepo.findByLivreurIdOrderByDateHeureDesc(2L)).thenReturn(Arrays.asList());

        List<LivreurPositionDTO> res = service.getAllLivreursWithLatestPosition();

        assertThat(res).hasSize(2);
        assertThat(res.stream().filter(r -> r.getLivreurId().equals(1L)).findFirst().get().getLatitude())
                .isEqualTo(10.0);
        assertThat(res.stream().filter(r -> r.getLivreurId().equals(2L)).findFirst().get().getLatitude()).isNull();
    }
}