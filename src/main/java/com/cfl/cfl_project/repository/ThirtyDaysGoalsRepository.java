package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.ThirtyDaysGoals;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThirtyDaysGoalsRepository extends JpaRepository<ThirtyDaysGoals,Long> {
    List<ThirtyDaysGoals> findByEmpIdAndQuarter(Long empId,String Quarter);
    List<ThirtyDaysGoals> findByEmpId(Long empId);


}
