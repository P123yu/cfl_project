package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.ProbationConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProbationConfirmationRepository extends JpaRepository<ProbationConfirmation,Long> {
    ProbationConfirmation findByEmployeeCode(Long cflId);
}
