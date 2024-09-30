package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.ProbationTracker;
import com.cfl.cfl_project.repository.ProbationTrackerRepository;
import com.cfl.cfl_project.service.ProbationTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProbationTrackerServiceImpl implements ProbationTrackerService {

    @Autowired
    private ProbationTrackerRepository probationTrackerRepository;

    @Override
    public ProbationTracker getTrackingInfoByCflId(Long cflId) {
        return probationTrackerRepository.findByCflId(cflId);
    }
}
