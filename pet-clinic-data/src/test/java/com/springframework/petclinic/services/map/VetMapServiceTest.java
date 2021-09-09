package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.services.SpecialityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VetMapServiceTest {

    @Mock
    SpecialityService specialityService;

    @InjectMocks
    VetMapService service;

    Vet returnVet;

    @BeforeEach
    void setUp() {
        returnVet = new Vet();
        returnVet.setId(1L);
        returnVet.setFirstName("Jacky");

        service.save(returnVet);

    }

    @Test
    void findAll() {
        Set<Vet> vetSet = service.findAll();

        assertEquals(1, vetSet.size());
    }

    @Test
    void deleteById() {
        service.deleteById(returnVet.getId());

        assertEquals(0, service.findAll().size());
    }

    @Test
    void delete() {
        service.delete(returnVet);

        assertEquals(0, service.findAll().size());
    }

    @Test
    void save() {
        Vet vetToSave = new Vet();
        vetToSave.setId(1L);

        Vet vet = service.save(vetToSave);

        assertEquals(returnVet.getId(), vet.getId());
    }

    @Test
    void findById() {
        Vet vet = service.findById(returnVet.getId());

        assertEquals(returnVet.getId(), vet.getId());
    }
}