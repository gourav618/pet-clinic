package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Speciality;
import com.springframework.petclinic.repositories.SpecialityRepository;
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
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    Speciality returnSpeciality;

    @BeforeEach
    void setUp() {
        returnSpeciality = new Speciality();
        returnSpeciality.setId(1L);
        returnSpeciality.setDescription("speciallity");
    }

    @Test
    void findAll() {
        Set<Speciality> specialitySet = new HashSet<>();
        specialitySet.add(returnSpeciality);

        when(specialityRepository.findAll()).thenReturn(specialitySet);

        Set<Speciality> specialities = service.findAll();

        assertNotNull(specialities);
        assertEquals(1,specialities.size());
    }

    @Test
    void findById() {
        when(specialityRepository.findById(anyLong())).thenReturn(Optional.of(returnSpeciality));

        Speciality speciality = service.findById(anyLong());

        assertNotNull(speciality);
    }

    @Test
    void save() {
        Speciality specialityToSave = new Speciality();
        specialityToSave.setId(1L);

        when(specialityRepository.save(any())).thenReturn(returnSpeciality);

        Speciality speciality = service.save(specialityToSave);

        assertNotNull(speciality);
        verify(specialityRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(any());

        verify(specialityRepository , times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        verify(specialityRepository, times(1)).deleteById(anyLong());
    }
}