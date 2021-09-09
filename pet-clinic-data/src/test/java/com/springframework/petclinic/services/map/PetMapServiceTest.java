package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class PetMapServiceTest {

    @InjectMocks
    PetMapService service;

    Pet returnPet;

    @BeforeEach
    void setUp() {
        service = new PetMapService();

        returnPet = new Pet();
        returnPet.setId(1L);
        returnPet.setName("Jacky");

        service.save(returnPet);
    }

    @Test
    void findAll() {
        Set<Pet> petSet = service.findAll();

        assertEquals(1, petSet.size());
    }

    @Test
    void deleteById() {
        service.deleteById(returnPet.getId());

        assertEquals(0, service.findAll().size());
    }

    @Test
    void delete() {

        service.delete(returnPet);

        assertEquals(0, service.findAll().size());

    }

    @Test
    void save() {
        Long id = returnPet.getId();

        Pet pet = new Pet();
        pet.setId(1L);

        Pet savedPet = service.save(pet);

        assertEquals(id, savedPet.getId());

    }

    @Test
    void findById() {
        Pet pet = service.findById(returnPet.getId());

        assertEquals(returnPet.getId(), pet.getId());
    }
}