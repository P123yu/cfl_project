package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.CflSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CflSkillRepository extends JpaRepository<CflSkill,Long> {
//    CflSkill findByEmpId(Long empId);
}
