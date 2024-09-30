package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.ThirtyDaysGoals;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ThirtyDaysGoalsService {

    // create
    List<ThirtyDaysGoals>createThirtyDaysGoals(Long empId,List<ThirtyDaysGoals>thirtyDaysGoals);

    // get
    List<ThirtyDaysGoals>getThirtyDaysGoalsByEmpIdAndQuarter(Long empId,String quarter);
}
