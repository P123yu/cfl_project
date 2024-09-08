package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.Cfl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CflRepository extends JpaRepository<Cfl,Long> {
//    List<Cfl>findByYear(String year);
//    Cfl findByEmpId(Long empId);
//    List<Cfl> findByMentorId(Long mentorId);
}
