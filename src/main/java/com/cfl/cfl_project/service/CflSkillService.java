package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.CflSkill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CflSkillService {

    CflSkill createCflSkill(CflSkill skill);
    CflSkill getCflSkillByEmpIdAndQuarter(Long id,String quarter);
    List<CflSkill> getAll();
}
