package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.Cfl;
import com.cfl.cfl_project.model.CflSkill;
import com.cfl.cfl_project.repository.CflRepository;
import com.cfl.cfl_project.repository.CflSkillRepository;
import com.cfl.cfl_project.service.CflSkillService;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CflSkillServiceImpl implements CflSkillService {

    @Autowired
    private CflSkillRepository cflSkillRepository;

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
    public CflSkill createCflSkill(CflSkill skill) {
        CflSkill cflSkill = new CflSkill();
        cflSkill.setEmpId(skill.getEmpId());
        cflSkill.setQuarter(getQuarter());
        cflSkill.setPrimarySkills(skill.getPrimarySkills());
        cflSkill.setSecondarySkills(skill.getSecondarySkills());
        cflSkill.setOtherSkills(skill.getOtherSkills());
        return cflSkillRepository.save(cflSkill);
    }

    @Override
    public CflSkill getCflSkillByEmpIdAndQuarter(Long id,String quarter) {
        return cflSkillRepository.findByEmpIdAndQuarter(id,quarter);
    }

    @Override
    public List<CflSkill> getAll() {
        return cflSkillRepository.findAll();
    }


}
