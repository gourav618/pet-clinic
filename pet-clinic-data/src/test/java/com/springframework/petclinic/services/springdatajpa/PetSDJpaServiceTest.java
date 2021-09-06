package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetSDJpaService service;

    Pet returnPet;

    @BeforeEach
    void setUp() {
//        returnPet = new Pet("Jacky", new PetType("Dog"), Owner.builder().id(1L).firstName("Gourav").build(),
//                LocalDate.now(), new HashSet<>());
        returnPet = new Pet();
        returnPet.setId(1L);
        returnPet.setName("Jacky");
        returnPet.setBirthDate(LocalDate.now());

    }

    @Test
    void findAll() {
        Set<Pet> petSet = new HashSet<>();
        petSet.add(returnPet);

        when(petRepository.findAll()).thenReturn(petSet);

        Set<Pet> pets = service.findAll();

        assertNotNull(pets);
        assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        when(petRepository.findById(anyLong())).thenReturn(Optional.of(returnPet));

        Pet pet = service.findById(1L);

        assertNotNull(pet);
    }

    @Test
    void save() {
        Pet petToSave = new Pet();
        petToSave.setId(1L);

        when(petRepository.save(any())).thenReturn(returnPet);

        Pet petSaved = service.save(petToSave);

        assertNotNull(petSaved);
        verify(petRepository).save(any());
    }

    @Test
    void delete() {

        service.delete(returnPet);

        verify(petRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        verify(petRepository, times(1)).deleteById(anyLong());
    }
}