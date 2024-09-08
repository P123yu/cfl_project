//package com.cfl.cfl_project.service.impl;
//
//import com.cfl.cfl_project.model.Cfl;
//import com.cfl.cfl_project.model.CflSkill;
//import com.cfl.cfl_project.repository.CflRepository;
//import com.cfl.cfl_project.repository.CflSkillRepository;
//import com.cfl.cfl_project.service.CflSkillService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CflSkillServiceImpl implements CflSkillService {
//
//    @Autowired
//    private CflSkillRepository cflSkillRepository;
//
//    @Override
//    public CflSkill createCflSkill(CflSkill skill) {
//        return cflSkillRepository.save(skill);
//    }
//
//
//
////    @Override
////    public CflSkill getCflSkillByEmpId(Long cflId) {
////        Cfl cfl=cflRepository.findByEmpId(cflId);
////        if(cfl!=null){
////            return cflSkillRepository.findByCfl(cfl);
////        }
////    }
//}
