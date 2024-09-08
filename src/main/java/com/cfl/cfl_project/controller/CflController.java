package com.cfl.cfl_project.controller;

import com.cfl.cfl_project.model.Cfl;
import com.cfl.cfl_project.model.MailHistory;
import com.cfl.cfl_project.service.CflService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cfl")
@CrossOrigin
public class CflController {

    @Autowired
    private CflService cflService;

    @PostMapping("/create")
    public ResponseEntity<?> createCfl(
            @RequestParam Long empId,
            @RequestParam String cflFirstName,
            @RequestParam String cflMiddleName,
            @RequestParam String cflLastName,
            @RequestParam String cflEmail,
            @RequestParam String cflDepartment,
            @RequestParam String cflDesignation,
            @RequestParam String reportingManager,
            @RequestParam String cflLocation,
            @RequestParam String joiningDate,

            @RequestParam String sscResult,
            @RequestParam String hscResult,
            @RequestParam String underGraduateResult,
            @RequestParam String postGraduateResult,
            @RequestParam String collegeName,
            @RequestParam String collegeBranch,
            @RequestParam String technicalSkills,
            @RequestParam String nonTechnicalSkills,
            @RequestParam MultipartFile file
    ) {
        try {
            Cfl cfl = cflService.createCfl(
                    empId,
                    cflFirstName,
                    cflMiddleName,
                    cflLastName,
                    cflEmail,
                    cflDepartment,
                    cflDesignation,
                    reportingManager,
                    cflLocation,
                    joiningDate,
                    sscResult,
                    hscResult,
                    underGraduateResult,
                    postGraduateResult,
                    collegeName,
                    collegeBranch,
                    technicalSkills,
                    nonTechnicalSkills,
                    file
            );
            return ResponseEntity.ok(cfl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File processing error");
        }
    }

//    @GetMapping("/getById/{empId}")
//    public ResponseEntity<?> getCflByEmpId(@PathVariable("empId") Long empId) {
//        Cfl cfl = cflService.getCflByEmpId(empId);
//        if (cfl != null) {
//            return ResponseEntity.ok(cfl);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cfl not found");
//        }
//    }
//
//    @GetMapping("/getAll")
//    public ResponseEntity<?> getAllCfl() {
//        List<Cfl> allCfl = cflService.getAllCfl();
//        if (!allCfl.isEmpty()) {
//            return ResponseEntity.ok(allCfl);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
//        }
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateCfl(
//            @PathVariable("id") Long id,
//            @RequestParam String cflFirstName,
//            @RequestParam String cflMiddleName,
//            @RequestParam String cflLastName,
//            @RequestParam String cflEmail,
//            @RequestParam String cflDepartment,
//            @RequestParam String cflDesignation,
//            @RequestParam String reportingManager,
//            @RequestParam String cflLocation,
//            @RequestParam LocalDate joiningDate,
//            @RequestParam String year,
//            @RequestParam String sscResult,
//            @RequestParam String hscResult,
//            @RequestParam String underGraduateResult,
//            @RequestParam String postGraduateResult,
//            @RequestParam String collegeName,
//            @RequestParam String collegeBranch,
//            @RequestParam String technicalSkills,
//            @RequestParam String nonTechnicalSkills,
//            @RequestParam(required = false) MultipartFile file
//    ) {
//        Cfl cflResponse = cflService.updateCflById(
//                id,
//                cflFirstName,
//                cflMiddleName,
//                cflLastName,
//                cflEmail,
//                cflDepartment,
//                cflDesignation,
//                reportingManager,
//                cflLocation,
//                joiningDate,
//                year,
//                sscResult,
//                hscResult,
//                underGraduateResult,
//                postGraduateResult,
//                collegeName,
//                collegeBranch,
//                technicalSkills,
//                nonTechnicalSkills,
//                file
//        );
//        if (cflResponse != null) {
//            return ResponseEntity.ok(cflResponse);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cfl not found");
//        }
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteCfl(@PathVariable("id") Long id) {
//        cflService.deleteCflById(id);
//        return ResponseEntity.ok("Cfl deleted successfully");
//    }
//
//    @GetMapping("/getAllByYear/{year}")
//    public ResponseEntity<?> getAllByYear(@PathVariable String year) {
//        List<Cfl> allCfl = cflService.getAllByYear(year);
//        if (!allCfl.isEmpty()) {
//            return ResponseEntity.ok(allCfl);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
//        }
//    }
//
//    @PostMapping("/sendMail")
//    public ResponseEntity<?> sentMailToMentor(@RequestParam Long empId,@RequestParam String mentorEmail,@RequestParam String subject, @RequestParam String message) {
//        Boolean result=cflService.sentMailToMentor(empId,mentorEmail,subject, message );
//        if(result){
//            return ResponseEntity.ok("Email sent successfully !!!");
//        }
//        else{
//            return ResponseEntity.badRequest().body("Email Not sent !!!");
//        }
//
//    }
//
//
//    @GetMapping("/getByMailHistoryByEmpId/{empId}")
//    public ResponseEntity<?> getByMailHistoryByEmpId(@PathVariable Long empId) {
//        List<MailHistory> mailHistoryList=cflService.getByMailHistoryByEmpId(empId);
//        if(mailHistoryList !=null){
//            return ResponseEntity.ok(mailHistoryList);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Mail History found for this Employee");
//        }
//    }
//
//
//    @PostMapping("/createMentorBasedOnEmpId")
//    public ResponseEntity<?> createMentor(@RequestParam Long mentorId,@RequestParam String empId, @RequestParam String mentorName, @RequestParam String mentorEmail, @RequestParam String mentorDepartment, @RequestParam String mentorLocation, @RequestParam String mentorDesignation,@RequestParam MultipartFile mentorFile) {
//       List<Cfl> mentorWithCfl=cflService.createMentor(mentorId, empId,mentorName, mentorEmail, mentorDepartment, mentorLocation, mentorDesignation, mentorFile);
//       if(!mentorWithCfl.isEmpty()){
//           return ResponseEntity.ok(mentorWithCfl);
//       }
//       else{
//           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Mentor creation failed");
//       }
//
//    }
//
//
//    @GetMapping("/getByMentorId/{mentorId}")
//    public ResponseEntity<?> getByMentorId(@PathVariable Long mentorId) {
//        List<Cfl>cflList=cflService.getByMentorId(mentorId);
//        if(!cflList.isEmpty()){
//            return ResponseEntity.ok(cflList);
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found for this Mentor");
//        }
//    }
//
//    @PostMapping("/sendMultipleEmail")
//    public ResponseEntity<?> sendMultipleEmail(@RequestParam String recipientEmails,@RequestParam String subject, @RequestParam String message) {
//        Boolean result=cflService.sendLinkToMultipleRecipients(recipientEmails, subject, message);
//        if(result){
//            return ResponseEntity.ok("Email sent successfully to multiple recipients!");
//        }
//        else{
//            return ResponseEntity.badRequest().body("Email Not sent to multiple recipients!");
//        }
//    }
}
