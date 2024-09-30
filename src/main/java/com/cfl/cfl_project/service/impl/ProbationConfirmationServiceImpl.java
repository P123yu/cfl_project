package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.ProbationConfirmation;
import com.cfl.cfl_project.repository.ProbationConfirmationRepository;
import com.cfl.cfl_project.service.ProbationConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProbationConfirmationServiceImpl implements ProbationConfirmationService {

    @Autowired
    private ProbationConfirmationRepository probationConfirmationRepository;

    @Override
    public ProbationConfirmation create(ProbationConfirmation probationConfirmation) {
        return probationConfirmationRepository.save(probationConfirmation);
    }

    @Override
    public ProbationConfirmation getByEmployeeCode(Long empId) {
        return probationConfirmationRepository.findByEmployeeCode(empId);
    }
}
