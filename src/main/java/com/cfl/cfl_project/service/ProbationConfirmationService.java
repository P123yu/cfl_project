package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.ProbationConfirmation;
import org.springframework.stereotype.Service;

@Service
public interface ProbationConfirmationService {

    ProbationConfirmation create(ProbationConfirmation probationConfirmation);
    ProbationConfirmation getByEmployeeCode(Long empId);
}
