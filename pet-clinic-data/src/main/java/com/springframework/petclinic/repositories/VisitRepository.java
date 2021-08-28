package com.springframework.petclinic.repositories;

import com.springframework.petclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {

}
