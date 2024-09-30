package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.ManagerRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRatingRepository extends JpaRepository<ManagerRating,Long> {
    ManagerRating findByEmpIdAndQuarter(Long empId,String quarter);
}
