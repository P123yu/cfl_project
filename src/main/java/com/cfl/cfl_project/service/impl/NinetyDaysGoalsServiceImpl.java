package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.NinetyDaysGoals;
import com.cfl.cfl_project.model.ThirtyDaysGoals;
import com.cfl.cfl_project.repository.NinetyDaysGoalsRepository;
import com.cfl.cfl_project.repository.ThirtyDaysGoalsRepository;
import com.cfl.cfl_project.service.NinetyDaysGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NinetyDaysGoalsServiceImpl implements NinetyDaysGoalsService {
    @Autowired
    private NinetyDaysGoalsRepository ninetyDaysGoalsRepository;

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
    public List<NinetyDaysGoals> createNinetyDaysGoals(Long empId, List<NinetyDaysGoals> ninetyDaysGoals) {
        List<NinetyDaysGoals>ninetyDaysGoalsLists=new ArrayList<>();
        for(NinetyDaysGoals i: ninetyDaysGoals){
            NinetyDaysGoals ninetyDaysGoals1=new NinetyDaysGoals();
            ninetyDaysGoals1.setEmpId(empId);
            ninetyDaysGoals1.setStatus(i.getStatus());
            ninetyDaysGoals1.setQuarter(getQuarter());
            ninetyDaysGoals1.setGoal(i.getGoal());
            ninetyDaysGoals1.setDeliverable(i.getDeliverable());
            ninetyDaysGoals1.setWeightage(i.getWeightage());
            ninetyDaysGoalsLists.add(ninetyDaysGoals1);
        }
        System.out.println(ninetyDaysGoalsLists+"ninetyDaysGoalsLists");
        return ninetyDaysGoalsRepository.saveAll(ninetyDaysGoalsLists);

    }

    @Override
    public List<NinetyDaysGoals> getNinetyDaysGoalsByEmpIdAndQuarter(Long empId,String quarter) {
        return ninetyDaysGoalsRepository.findByEmpIdAndQuarter(empId,quarter);
    }
}
