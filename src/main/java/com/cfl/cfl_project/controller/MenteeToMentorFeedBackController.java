package com.cfl.cfl_project.controller;


import com.cfl.cfl_project.model.CustomMenteeToMentorFeedBack;
import com.cfl.cfl_project.model.MenteeToMentorFeedBack;
import com.cfl.cfl_project.service.MenteeToMentorFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menteeToMentorFeedBack")
@CrossOrigin("*")
public class MenteeToMentorFeedBackController {

    @Autowired
    private MenteeToMentorFeedbackService menteeToMentorFeedBackService;

    @PostMapping("/create")
    public ResponseEntity<Object> createMenteeToMentorFeedBack(@RequestBody  MenteeToMentorFeedBack menteeToMentorFeedBack) {
        MenteeToMentorFeedBack menteeToMentorFeedBackRequest=menteeToMentorFeedBackService.createFeedBack(menteeToMentorFeedBack);
        if(menteeToMentorFeedBackRequest !=null){
            return ResponseEntity.ok(menteeToMentorFeedBackRequest);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create MenteeToMentorFeedBack");
        }
    }


    @GetMapping("/getAllByHr")
    public ResponseEntity<Object> getAll() {
        List<CustomMenteeToMentorFeedBack> customMenteeToMentorFeedBackLists=menteeToMentorFeedBackService.getAllFeedBack();
        if(!customMenteeToMentorFeedBackLists.isEmpty()){
            return ResponseEntity.ok(customMenteeToMentorFeedBackLists);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create CustomMenteeToMentorFeedBack");
        }
    }
}
