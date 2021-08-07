package com.springframework.petclinic.services;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
