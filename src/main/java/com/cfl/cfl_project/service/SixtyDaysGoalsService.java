package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.SixtyDaysGoals;
import com.cfl.cfl_project.model.ThirtyDaysGoals;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SixtyDaysGoalsService {
    // create
    List<SixtyDaysGoals> createSixtyDaysGoals(Long empId, List<SixtyDaysGoals>sixtyDaysGoals);

    // get
    List<SixtyDaysGoals>getSixtyDaysGoalsByEmpIdAndQuarter(Long empId,String quarter);
}
