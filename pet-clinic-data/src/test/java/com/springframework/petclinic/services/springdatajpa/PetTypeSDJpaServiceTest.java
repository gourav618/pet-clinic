package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.repositories.PetTypeRepository;
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
class PetTypeSDJpaServiceTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeSDJpaService service;

    PetType returnpetType;

    @BeforeEach
    void setUp() {
        returnpetType = new PetType();
        returnpetType.setId(1L);
        returnpetType.setName("Dog");
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = new HashSet<>();
        petTypeSet.add(returnpetType);

        when(petTypeRepository.findAll()).thenReturn(petTypeSet);

        Set<PetType> petTypes = service.findAll();

        assertNotNull(petTypes);
        assertEquals(1, petTypes.size());
    }

    @Test
    void findById() {
        when(petTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnpetType));

        PetType petType = service.findById(anyLong());

        assertNotNull(petType);
    }

    @Test
    void save() {
        PetType petTypeToSave = new PetType();
        petTypeToSave.setId(1L);

        when(petTypeRepository.save(any())).thenReturn(returnpetType);

        PetType petType = service.save(petTypeToSave);

        assertNotNull(petType);
        verify(petTypeRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnpetType);

        verify(petTypeRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {

        service.deleteById(anyLong());

        verify(petTypeRepository, times(1)).deleteById(anyLong());
    }
}