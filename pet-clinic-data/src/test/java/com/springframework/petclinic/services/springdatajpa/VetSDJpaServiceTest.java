package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VetSDJpaServiceTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetSDJpaService service;

    Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = new Vet();
        returnVet.setId(1L);
        returnVet.setFirstName("vetA");
    }

    @Test
    void findAll() {
        Set<Vet> vetSet = new HashSet<>();
        vetSet.add(returnVet);

        when(vetRepository.findAll()).thenReturn(vetSet);

        Set<Vet> vets = service.findAll();

        assertNotNull(vets);
        assertEquals(1,vets.size());
    }

    @Test
    void findById() {
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(returnVet));

        Vet vets = service.findById(anyLong());

        assertNotNull(vets);
    }

    @Test
    void save() {
        Vet vetToSave = new Vet();
        vetToSave.setId(1L);

        when(vetRepository.save(any())).thenReturn(returnVet);

        Vet vet = service.save(vetToSave);

        assertNotNull(vet);
        verify(vetRepository).save(any());
    }

    @Test
    void delete() {

        service.delete(any());

        verify(vetRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        verify(vetRepository, times(1)).deleteById(anyLong());
    }
}