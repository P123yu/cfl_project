package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.ProbationConfirmation;
import com.cfl.cfl_project.service.ProbationConfirmationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probationConfirmation")
@CrossOrigin("*")

public class ProbationConfirmationController {

    @Autowired
    private ProbationConfirmationService probationConfirmationService;

    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody ProbationConfirmation probationConfirmation){
        ProbationConfirmation probationConfirmationObj=probationConfirmationService.create(probationConfirmation);
        if(probationConfirmationObj!=null){
            return ResponseEntity.ok(probationConfirmationObj);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create ProbationConfirmation");
        }
    }



    @GetMapping("/getByEmpId/{empId}")
    public ResponseEntity<?>getByEmployeeCode(@PathVariable Long empId){
        ProbationConfirmation probationConfirmationObj=probationConfirmationService.getByEmployeeCode(empId);
        if(probationConfirmationObj!=null){
            return ResponseEntity.ok(probationConfirmationObj);
        }
        else{
            return ResponseEntity.status(400).body("Failed to get Probation Confirmation");
        }
    }
}
