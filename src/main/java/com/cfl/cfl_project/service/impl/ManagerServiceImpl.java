package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.email.CflToMentorMail;
import com.cfl.cfl_project.model.*;
import com.cfl.cfl_project.repository.*;
import com.cfl.cfl_project.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {


    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private CflRepository cflRepository;

    @Override
    public Manager createManager(Long managerId, String managerName, String managerEmail, String managerDepartment, String managerLocation, String managerDesignation, MultipartFile managerFile) throws IOException {
        Cfl cfl=new Cfl();
//        cfl.setManagerEmail(managerEmail);
        cfl.setReportingManagerMail(managerEmail);
        Manager manager =new Manager();
        manager.setManagerId(managerId);
        manager.setManagerName(managerName);
        manager.setManagerEmail(managerEmail);
        manager.setManagerDepartment(managerDepartment);
        manager.setManagerLocation(managerLocation);
        manager.setManagerDesignation(managerDesignation);
        manager.setManagerFileName(managerFile.getOriginalFilename());
        manager.setManagerFileData(managerFile.getBytes());
        return managerRepository.save(manager);
    }

    @Override
    public Manager getManagerByManagerMail(String managerEmail) {
        return managerRepository.findByManagerEmail(managerEmail);
    }

    @Autowired
    private ThirtyDaysGoalsRepository thirtyDaysGoalsRepository;

    @Autowired
    private SixtyDaysGoalsRepository sixtyDaysGoalsRepository;

    @Autowired
    private NinetyDaysGoalsRepository ninetyDaysGoalsRepository;


    @Autowired
    private CflToMentorMail cflToMentorMail;

    @Async
    @Override
    public String createEmailOnApprove(Long cflId) {
        List<ThirtyDaysGoals>allThirtyDaysGoalsByEmpId=thirtyDaysGoalsRepository.findByEmpId(cflId);
        List<SixtyDaysGoals>allSixtyDaysGoalsByEmpId=sixtyDaysGoalsRepository.findByEmpId(cflId);
        List<NinetyDaysGoals>allNinetyDaysGoalsByEmpId=ninetyDaysGoalsRepository.findByEmpId(cflId);
        allThirtyDaysGoalsByEmpId.forEach(i->i.setStatus("approved"));
        allSixtyDaysGoalsByEmpId.forEach(i->i.setStatus("approved"));
        allNinetyDaysGoalsByEmpId.forEach(i->i.setStatus("approved"));
        thirtyDaysGoalsRepository.saveAll(allThirtyDaysGoalsByEmpId);
        sixtyDaysGoalsRepository.saveAll(allSixtyDaysGoalsByEmpId);
        ninetyDaysGoalsRepository.saveAll(allNinetyDaysGoalsByEmpId);
        String subject="Regarding Goal Setting";
        String message="Your goal setting has been approved";

        String cflEmail=cflRepository.findByEmpId(cflId).getCflEmail();
        cflToMentorMail.sendGoalSetting(cflEmail,subject,message);
        return "approved";
    }

}
