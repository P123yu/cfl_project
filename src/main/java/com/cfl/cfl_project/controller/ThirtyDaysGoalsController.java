package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.ThirtyDaysGoals;
import com.cfl.cfl_project.service.ThirtyDaysGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Goals/thirty-days-goals")
public class ThirtyDaysGoalsController {

    @Autowired
    private ThirtyDaysGoalsService thirtyDaysGoalsService;

    // Endpoint to create new thirty days goals
    @PostMapping("/emp/{empId}")
    public ResponseEntity<List<ThirtyDaysGoals>> createThirtyDaysGoals(
            @PathVariable Long empId,
            @RequestBody List<ThirtyDaysGoals> thirtyDaysGoals) {

        List<ThirtyDaysGoals> createdGoals = thirtyDaysGoalsService.createThirtyDaysGoals(empId, thirtyDaysGoals);
        if(!createdGoals.isEmpty()){
            return new ResponseEntity<>(createdGoals, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    // Endpoint to get thirty days goals by employee ID
    @GetMapping("/emp/{empId}/{quarter}")
    public ResponseEntity<List<ThirtyDaysGoals>> getThirtyDaysGoalsByEmpIdAndQuarter(@PathVariable Long empId,@PathVariable String quarter) {
        List<ThirtyDaysGoals> goals = thirtyDaysGoalsService.getThirtyDaysGoalsByEmpIdAndQuarter(empId,quarter);
        if (goals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }





}
