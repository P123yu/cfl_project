package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.GoalSettingTracker;
import org.springframework.stereotype.Service;

@Service
public interface GoalSettingTrackerService {

    // track goal settings
    GoalSettingTracker getTrackingInfoByCflIdAndQuarter(Long cflId,String quarter);
}
