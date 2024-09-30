package com.cfl.cfl_project.repository;

import com.cfl.cfl_project.model.Cfl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CflRepository extends JpaRepository<Cfl,Long> {
    List<Cfl>findByYear(String year);
    Cfl findByEmpId(Long empId);
    //    List<Cfl> findByManagerEmail(String managerEmail);
    List<Cfl> findByReportingManagerMail(String reportingManagerMail);
    Cfl findByCflEmail(String cflEmail);
    List<Cfl>findByGoalSettingStatusHrToMgr(Boolean status);
    List<Cfl>findByProbationStatus(Boolean probationStatus);
    Cfl findByHrMail(String hrMail);
    List<Cfl> findAllByYear(String year);
    List<Cfl> findAllByCflCountStatus(Boolean status);
    Cfl findByCflFirstNameAndCflLastName(String cflFirstName,String cflLastName);
}
