package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VisitMapServiceTest {

    @InjectMocks
    VisitMapService service;

    Visit returnVisit;

    Pet pet;

    @BeforeEach
    void setUp() {
        service = new VisitMapService();

        pet = new Pet();
        pet.setId(1L);
        pet.setOwner(Owner.builder().id(1L).build());

        returnVisit = new Visit();
        returnVisit.setId(1L);
        returnVisit.setDescription("visit");
        returnVisit.setPet(pet);

        service.save(returnVisit);
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = service.findAll();

        assertEquals(1, visitSet.size());
    }

    @Test
    void deleteById() {
        service.deleteById(returnVisit.getId());

        assertEquals(0, service.findAll().size());
    }

    @Test
    void delete() {
        service.delete(returnVisit);

        assertEquals(0, service.findAll().size());
    }

    @Test
    void save() {

        Visit visit = service.save(returnVisit);

        assertEquals(returnVisit.getId(), visit.getId());

    }

    @Test
    void findById() {
        Visit visit = service.findById(returnVisit.getId());

        assertEquals(returnVisit.getId(), visit.getId());
    }
}