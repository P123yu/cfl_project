package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.ManagerRating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerRatingService {
    ManagerRating createManagerRating(ManagerRating managerRating);
    ManagerRating getManagerRatingByEmpIdAndQuarter(Long id,String quarter);
    List<ManagerRating> getAll();
}
