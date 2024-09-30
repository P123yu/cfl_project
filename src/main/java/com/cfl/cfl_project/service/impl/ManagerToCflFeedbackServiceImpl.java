package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.ManagerToCflFeedBack;
import com.cfl.cfl_project.repository.CflRepository;
import com.cfl.cfl_project.repository.ManagerRepository;
import com.cfl.cfl_project.repository.ManagerToCflFeedbackRepository;
import com.cfl.cfl_project.service.ManagerToCflFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ManagerToCflFeedbackServiceImpl implements ManagerToCflFeedbackService {

    @Autowired
    private ManagerToCflFeedbackRepository managerToCflFeedbackRepository;

    @Autowired
    private CflRepository cflRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public ManagerToCflFeedBack createFeedBack(ManagerToCflFeedBack managerToCflFeedBack) {
        ManagerToCflFeedBack managerToCflFeedBackObj=new ManagerToCflFeedBack();
        managerToCflFeedBackObj.setMenteeId(managerToCflFeedBack.getMenteeId());
        System.out.println(managerToCflFeedBack.getManagerEmail());
        managerToCflFeedBackObj.setMenteeName(cflRepository.findByEmpId(managerToCflFeedBack.getMenteeId()).getCflFirstName());
        managerToCflFeedBackObj.setManagerEmail(managerToCflFeedBack.getManagerEmail());
        managerToCflFeedBackObj.setManagerName(managerRepository.findByManagerEmail(managerToCflFeedBack.getManagerEmail()).getManagerName());
        managerToCflFeedBackObj.setFeedbackMessage(managerToCflFeedBack.getFeedbackMessage());
        LocalDate date= LocalDate.now();
        managerToCflFeedBackObj.setFeedbackDate(date);
        return managerToCflFeedbackRepository.save(managerToCflFeedBackObj);
    }

    @Override
    public List<ManagerToCflFeedBack> getAllFeedBack() {
        return managerToCflFeedbackRepository.findAll();
    }
}
