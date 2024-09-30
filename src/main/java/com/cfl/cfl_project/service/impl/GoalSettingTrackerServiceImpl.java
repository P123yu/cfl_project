package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.GoalSettingTracker;
import com.cfl.cfl_project.service.GoalSettingTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cfl.cfl_project.repository.GoalSettingTrackerRepository;
import org.springframework.stereotype.Service;

@Service
public class GoalSettingTrackerServiceImpl implements GoalSettingTrackerService {

    @Autowired
    private GoalSettingTrackerRepository goalSettingTrackerRepository;

    @Override
    public GoalSettingTracker getTrackingInfoByCflIdAndQuarter(Long cflId, String quarter) {
        return goalSettingTrackerRepository.findByCflIdAndQuarter(cflId, quarter);
    }
}
