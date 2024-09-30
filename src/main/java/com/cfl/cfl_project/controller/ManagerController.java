package com.cfl.cfl_project.controller;


import com.cfl.cfl_project.model.Manager;
import com.cfl.cfl_project.model.Mentor;
import com.cfl.cfl_project.model.MentorResponse;
import com.cfl.cfl_project.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/manager")
@CrossOrigin("*")

public class ManagerController {

    @Autowired
    private ManagerService managerService;



    @PostMapping("/create")
    public ResponseEntity<?>createManager(@RequestParam Long managerId,@RequestParam String managerName,@RequestParam String managerEmail,@RequestParam String managerDepartment,@RequestParam String managerLocation,@RequestParam String managerDesignation,@RequestPart MultipartFile managerFile) throws IOException {
        Manager manager = managerService.createManager(managerId, managerName, managerEmail, managerDepartment, managerLocation, managerDesignation, managerFile);
        if(manager!=null){
            return ResponseEntity.ok(manager);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create manager");
        }
    }

    @GetMapping("/{managerEmail}")
    public ResponseEntity<?> getMentorByEmail(@PathVariable("managerEmail") String managerEmail){
        Manager manager= managerService.getManagerByManagerMail(managerEmail);
        if(manager!=null){
            return ResponseEntity.ok(manager);
        }
        else{
            return ResponseEntity.status(500).body("manager Response not found");
        }
    }


    @GetMapping("/approvedGoalSetting/{cflId}")
    public ResponseEntity<?> getApprovedGoalSetting(@PathVariable Long cflId){
        String approved= managerService.createEmailOnApprove(cflId);
        if(!approved.isEmpty()){
            return ResponseEntity.ok(approved);
        }
        else{
            return ResponseEntity.status(500).body("Approve Response not found");
        }
    }
}
