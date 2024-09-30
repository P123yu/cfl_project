package com.cfl.cfl_project.controller;


import com.cfl.cfl_project.model.CflSkill;
import com.cfl.cfl_project.service.CflSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cflSkill")
@CrossOrigin("*")
public class CflSkillController {

    @Autowired
    private CflSkillService cflSkillService;

    @PostMapping("/create")
    public ResponseEntity<?> createCflSkill(@RequestBody CflSkill cflSkill){
        CflSkill cflSkillObj=cflSkillService.createCflSkill(cflSkill);
        if(cflSkillObj !=null){
            return ResponseEntity.ok(cflSkillObj);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create Cfl Skill");
        }
    }



    @GetMapping("/get/{empId}/{quarter}")
    public ResponseEntity<?> getCflSkillByIdAndQuarter(@PathVariable("empId") Long id,@PathVariable String quarter){
        CflSkill cflSkillObj=cflSkillService.getCflSkillByEmpIdAndQuarter(id,quarter);
        if(cflSkillObj !=null){
            return ResponseEntity.ok(cflSkillObj);
        }
        else{
            return ResponseEntity.status(400).body("Failed to get Cfl Skill");
        }
    }

    @GetMapping("/getAllCflSkill")
    public ResponseEntity<?> getAllCflSkills(){
        List<CflSkill> cflSkills=cflSkillService.getAll();
        if(!cflSkills.isEmpty()){
            return ResponseEntity.ok(cflSkills);
        }
        else{
            return ResponseEntity.status(400).body("Failed to get all Cfl Skills");
        }
    }
}
