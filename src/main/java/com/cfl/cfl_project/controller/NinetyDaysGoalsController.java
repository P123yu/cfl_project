package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.NinetyDaysGoals;
import com.cfl.cfl_project.model.ThirtyDaysGoals;
import com.cfl.cfl_project.service.NinetyDaysGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Goals/ninety-days-goals")
public class NinetyDaysGoalsController {

    @Autowired
    private NinetyDaysGoalsService ninetyDaysGoalsService;

    @PostMapping("/emp/{empId}")
    public ResponseEntity<?> createNinetyDaysGoals(
            @PathVariable Long empId,
            @RequestBody List<NinetyDaysGoals> ninetyDaysGoals) {

        List<NinetyDaysGoals> createdGoals = ninetyDaysGoalsService.createNinetyDaysGoals(empId, ninetyDaysGoals);
        if(!createdGoals.isEmpty()){
            return new ResponseEntity<>(createdGoals, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emp/{empId}/{quarter}")
    public ResponseEntity<?> getNinetyDaysGoalsByEmpIdByQuarter(@PathVariable Long empId,@PathVariable String quarter) {
        List<NinetyDaysGoals> goals = ninetyDaysGoalsService.getNinetyDaysGoalsByEmpIdAndQuarter(empId,quarter);
        if (goals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }
}
