package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.GoalSettingTracker;
import com.cfl.cfl_project.model.ProbationTracker;
import org.springframework.stereotype.Service;

@Service
public interface ProbationTrackerService {
    // track probation settings
    ProbationTracker getTrackingInfoByCflId(Long cflId);
}
