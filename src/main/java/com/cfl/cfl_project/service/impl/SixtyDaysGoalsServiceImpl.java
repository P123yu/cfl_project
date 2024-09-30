package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.SixtyDaysGoals;
import com.cfl.cfl_project.model.ThirtyDaysGoals;
import com.cfl.cfl_project.repository.SixtyDaysGoalsRepository;
import com.cfl.cfl_project.repository.ThirtyDaysGoalsRepository;
import com.cfl.cfl_project.service.SixtyDaysGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SixtyDaysGoalsServiceImpl implements SixtyDaysGoalsService {
    @Autowired
    private SixtyDaysGoalsRepository sixtyDaysGoalsRepository;

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
    public List<SixtyDaysGoals> createSixtyDaysGoals(Long empId, List<SixtyDaysGoals> sixtyDaysGoals) {
        System.out.println(empId+"empId");
        List<SixtyDaysGoals>sixtyDaysGoalsLists=new ArrayList<>();
        for(SixtyDaysGoals i: sixtyDaysGoals){
            SixtyDaysGoals sixtyDaysGoals1=new SixtyDaysGoals();
            sixtyDaysGoals1.setEmpId(empId);
            sixtyDaysGoals1.setStatus(i.getStatus());
            sixtyDaysGoals1.setQuarter(getQuarter());
            sixtyDaysGoals1.setGoal(i.getGoal());
            sixtyDaysGoals1.setDeliverable(i.getDeliverable());
            sixtyDaysGoals1.setWeightage(i.getWeightage());
            sixtyDaysGoalsLists.add(sixtyDaysGoals1);
        }
        System.out.println(sixtyDaysGoalsLists+"sixtyDaysGoalsLists");
        return sixtyDaysGoalsRepository.saveAll(sixtyDaysGoalsLists);
    }

    @Override
    public List<SixtyDaysGoals> getSixtyDaysGoalsByEmpIdAndQuarter(Long empId,String quarter) {
        return sixtyDaysGoalsRepository.findByEmpIdAndQuarter(empId,quarter);
    }

}
