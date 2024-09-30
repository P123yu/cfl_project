package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.ThirtyDaysGoals;
import com.cfl.cfl_project.service.ThirtyDaysGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import com.cfl.cfl_project.repository.ThirtyDaysGoalsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ThirtyDaysGoalsServiceImpl implements ThirtyDaysGoalsService {

    @Autowired
    private ThirtyDaysGoalsRepository thirtyDaysGoalsRepository;

    //
    public static String getQuarter() {
        LocalDate date=LocalDate.now();
        int month = date.getMonthValue();

        if (month >= 1 && month <= 3) {
            return "Q4";
        } else if (month >= 4 && month <= 6) {
            return "Q1";
        } else if (month >= 7 && month <= 9) {
            return "Q2";
        } else if (month >= 10 && month <= 12) {
            return "Q3";
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }


    @Override
    public List<ThirtyDaysGoals> createThirtyDaysGoals(Long empId,List<ThirtyDaysGoals> thirtyDaysGoals) {
        System.out.println(empId+"empId");
       List<ThirtyDaysGoals>thirtyDaysGoalsLists=new ArrayList<>();
        for(ThirtyDaysGoals i: thirtyDaysGoals){
            ThirtyDaysGoals thirtyDaysGoals1=new ThirtyDaysGoals();
            thirtyDaysGoals1.setEmpId(empId);
            thirtyDaysGoals1.setStatus(i.getStatus());
            thirtyDaysGoals1.setQuarter(getQuarter());
            thirtyDaysGoals1.setGoal(i.getGoal());
            thirtyDaysGoals1.setDeliverable(i.getDeliverable());
            thirtyDaysGoals1.setWeightage(i.getWeightage());
            thirtyDaysGoalsLists.add(thirtyDaysGoals1);
        }
        System.out.println(thirtyDaysGoalsLists+"thirtyDaysGoalsLists");
        return thirtyDaysGoalsRepository.saveAll(thirtyDaysGoalsLists);
    }

    @Override
    public List<ThirtyDaysGoals> getThirtyDaysGoalsByEmpIdAndQuarter(Long empId,String quarter) {
        return thirtyDaysGoalsRepository.findByEmpIdAndQuarter(empId,quarter);
    }
}
