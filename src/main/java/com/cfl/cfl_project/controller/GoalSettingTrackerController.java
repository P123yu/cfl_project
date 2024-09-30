package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.GoalSettingTracker;
import com.cfl.cfl_project.service.GoalSettingTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goalSettingTracker")
@CrossOrigin("*")
public class GoalSettingTrackerController {

    @Autowired
    private GoalSettingTrackerService goalSettingTrackerService;

    @GetMapping("/{cflId}/{quarter}")
    public ResponseEntity<?> getAllGoalSettingTrackers(@PathVariable Long cflId,@PathVariable String quarter) {
        System.out.println(cflId+""+quarter);
       GoalSettingTracker goalSettingTracker=goalSettingTrackerService.getTrackingInfoByCflIdAndQuarter(cflId,quarter);
       if(goalSettingTracker!=null){
           return ResponseEntity.ok(goalSettingTracker);
       }
       else{
           return ResponseEntity.status(400).body("Failed to get Goal Setting Tracker");
       }
    }
}
