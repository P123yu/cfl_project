package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.GoalSettingTracker;
import com.cfl.cfl_project.model.ProbationTracker;
import com.cfl.cfl_project.service.GoalSettingTrackerService;
import com.cfl.cfl_project.service.ProbationTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probationTracker")
@CrossOrigin("*")
public class ProbationTrackerController {

  @Autowired
  private ProbationTrackerService probationTrackerService;

    @GetMapping("/{cflId}")
    public ResponseEntity<?> getTrackingInfoByCflId(@PathVariable Long cflId) {
        ProbationTracker probationTracker=probationTrackerService.getTrackingInfoByCflId(cflId);
        if(probationTracker!=null){
            return ResponseEntity.ok(probationTracker);
        }
        else{
            return ResponseEntity.status(400).body("Failed to get probation Tracker");
        }
    }
}
