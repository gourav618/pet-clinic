package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Speciality;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SpecialityMapServiceTest {

    @InjectMocks
    SpecialityMapService service;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        service = new SpecialityMapService();

        returnSpeciality = new Speciality();
        returnSpeciality.setId(1L);

        service.save(returnSpeciality);
    }

    @Test
    void findAll() {
        Set<Speciality> specialitySet = service.findAll();

        assertEquals(1,specialitySet.size());
    }

    @Test
    void deleteById() {
        service.deleteById(returnSpeciality.getId());

        assertEquals(0,service.findAll().size());
    }

    @Test
    void delete() {
        service.delete(returnSpeciality);

        assertEquals(0,service.findAll().size());
    }

    @Test
    void save() {
        Speciality specialityToSave = new Speciality();
        specialityToSave.setId(1L);

        Speciality speciality = service.save(specialityToSave);

        assertNotNull(speciality);
        assertEquals(returnSpeciality.getId(), speciality.getId());
    }

    @Test
    void findById() {
        Speciality speciality = service.findById(returnSpeciality.getId());

        assertEquals(returnSpeciality.getId(), speciality.getId());
    }
}