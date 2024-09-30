package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.ManagerRating;
import com.cfl.cfl_project.service.ManagerRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managerRating")
@CrossOrigin("*")

public class ManagerRatingController {

    @Autowired
    private ManagerRatingService managerRatingService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ManagerRating managerRating){
        ManagerRating managerRating1=managerRatingService.createManagerRating(managerRating);
        if(managerRating1 !=null){
            return ResponseEntity.ok(managerRating1);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create Manager Rating");
        }
    }


    @GetMapping("/get/{empId}/{quarter}")
    public ResponseEntity<?> getManagerRatingByEmpIdAndQuarter(@PathVariable Long empId,@PathVariable String quarter) {
        ManagerRating managerRating1 = managerRatingService.getManagerRatingByEmpIdAndQuarter(empId,quarter);
        if (managerRating1 != null) {
            return ResponseEntity.ok(managerRating1);
        } else {
            return ResponseEntity.status(400).body("Failed to create Manager Rating");
        }
    }


    @GetMapping("/getAllManagerRating")
    public ResponseEntity<?>getAllManagerRating() {
       List<ManagerRating>managerRatings= managerRatingService.getAll();
       if(!managerRatings.isEmpty()) {
           return ResponseEntity.ok(managerRatings);
       }
           else{
               return ResponseEntity.status(400).body("Failed to create Manager Rating");
           }
       }
}
