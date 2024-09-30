package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.ManagerRating;
import com.cfl.cfl_project.repository.ManagerRatingRepository;
import com.cfl.cfl_project.service.ManagerRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ManagerRatingServiceImpl implements ManagerRatingService {

    @Autowired
    private ManagerRatingRepository managerRatingRepository;

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
    public ManagerRating createManagerRating(ManagerRating managerRating) {
        ManagerRating newManagerRating = new ManagerRating();
        newManagerRating.setEmpId(managerRating.getEmpId());
        newManagerRating.setInternal1(managerRating.getInternal1());
        newManagerRating.setInternal2(managerRating.getInternal2());
        newManagerRating.setInternal3(managerRating.getInternal3());
        newManagerRating.setProject1(managerRating.getProject1());
        newManagerRating.setProject2(managerRating.getProject2());
        newManagerRating.setProject3(managerRating.getProject3());
        newManagerRating.setTalentLevel(managerRating.getTalentLevel());
        newManagerRating.setPotentialLevel(managerRating.getPotentialLevel());
        newManagerRating.setPerformanceLevel(managerRating.getPerformanceLevel());
        newManagerRating.setQuarter(getQuarter());
        return managerRatingRepository.save(newManagerRating);
    }

    @Override
    public ManagerRating getManagerRatingByEmpIdAndQuarter(Long id,String quarter) {
        return managerRatingRepository.findByEmpIdAndQuarter(id,quarter);
    }

    @Override
    public List<ManagerRating> getAll() {
        return managerRatingRepository.findAll();
    }
}
