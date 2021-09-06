package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = new Visit();
        returnVisit.setId(1L);
        returnVisit.setDescription("visits 1");
    }

    @Test
    void findAll() {
        Set<Visit> visitSet = new HashSet<>();
        visitSet.add(returnVisit);

        when(visitRepository.findAll()).thenReturn(visitSet);

        Set<Visit> visits = service.findAll();

        assertNotNull(visits);
        assertEquals(1, visits.size());
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));

        Visit visit = service.findById(anyLong());

        assertNotNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = new Visit();
        visitToSave.setId(1L);

        when(visitRepository.save(any())).thenReturn(returnVisit);

        Visit visit = service.save(visitToSave);

        assertNotNull(visit);
        verify(visitRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(any());

        verify(visitRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());

        verify(visitRepository, times(1)).deleteById(anyLong());
    }
}