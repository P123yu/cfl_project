package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.Mentor;
import com.cfl.cfl_project.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/mentor")
@CrossOrigin("*")
public class MentorController {

    @Autowired
    private MentorService mentorService;

    // search in register

    @GetMapping("/get/{mentorEmail}")
    private ResponseEntity<?> getByUserName(@PathVariable("mentorEmail") String userName){
        Boolean mentorResponse=mentorService.getByUserName(userName);
        if(mentorResponse){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }

    }



    @GetMapping("/getMentorEmail/{mentorEmail}")
    private ResponseEntity<?> getByMentorEmail(@PathVariable("mentorEmail") String mentorEmail){
        Boolean mentorResponse=mentorService.getByMentorEmail(mentorEmail);
        if(mentorResponse){
            return ResponseEntity.ok(true);
        }
        else{
            return ResponseEntity.ok(false);
        }

    }


    @PostMapping("/create")
    public ResponseEntity<?> createMentor(
            @RequestParam("mentorId") Long mentorId,
            @RequestParam("empId") String empId,
            @RequestParam("mentorName") String mentorName,
            @RequestParam("mentorEmail") String mentorEmail,
            @RequestParam("mentorDepartment") String mentorDepartment,
            @RequestParam("mentorLocation") String mentorLocation,
            @RequestParam("mentorDesignation") String mentorDesignation,
            @RequestParam("mentorFile") MultipartFile mentorFile) throws IOException {

            Mentor mentor = mentorService.createMentor(mentorId, empId, mentorName, mentorEmail,
                    mentorDepartment, mentorLocation, mentorDesignation, mentorFile);
            if(mentor != null){
                return ResponseEntity.ok(mentor);
            }
            else{
                return ResponseEntity.status(500).body(null);
            }
    }


}
