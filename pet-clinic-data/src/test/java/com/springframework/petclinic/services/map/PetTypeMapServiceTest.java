package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PetTypeMapServiceTest {

    @InjectMocks
    PetTypeMapService service;

    PetType returnPetType;

    @BeforeEach
    void setUp() {
        service = new PetTypeMapService();

        returnPetType = new PetType();
        returnPetType.setId(1L);
        returnPetType.setName("Dog");

        service.save(returnPetType);
    }

    @Test
    void findAll() {
        Set<PetType> petTypeSet = service.findAll();

        assertEquals(1,petTypeSet.size());
    }

    @Test
    void deleteById() {
        service.deleteById(returnPetType.getId());

        assertEquals(0, service.findAll().size());
    }

    @Test
    void delete() {
        service.delete(returnPetType);

        assertEquals(0, service.findAll().size());
    }

    @Test
    void save() {
        PetType petType = new PetType();
        petType.setId(1L);

        PetType savePetType = service.save(petType);

        assertEquals(returnPetType.getId(), savePetType.getId());

    }

    @Test
    void findById() {
        PetType petType = service.findById(returnPetType.getId());

        assertNotNull(petType);
    }
}