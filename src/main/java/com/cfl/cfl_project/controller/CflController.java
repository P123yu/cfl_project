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
import java.util.List;

@RestController
@RequestMapping("/cfl")
@CrossOrigin("*")
public class CflController {

    @Autowired
    private CflService cflService;
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createCfl(
//            @RequestParam Long empId,
//            @RequestParam String cflFirstName,
//            @RequestParam String cflMiddleName,
//            @RequestParam String cflLastName,
//            @RequestParam String cflEmail,
//            @RequestParam String cflDepartment,
//            @RequestParam String cflDesignation,
//            @RequestParam String reportingManager,
//            @RequestParam String reportingManagerMail,
//            @RequestParam String hrMail,
//            @RequestParam String cflLocation,
//            @RequestParam String joiningDate,
//            @RequestParam String sscResult,
//            @RequestParam String hscResult,
//            @RequestParam String underGraduateResult,
//            @RequestParam String postGraduateResult,
//            @RequestParam String collegeName,
//            @RequestParam String collegeBranch,
//            @RequestParam String technicalSkills,
//            @RequestParam String nonTechnicalSkills
//    ) {
//        try {
//            Cfl cfl = cflService.createCfl(
//                    empId, cflFirstName, cflMiddleName,
//                    cflLastName, cflEmail, cflDepartment,
//                    cflDesignation, reportingManager, reportingManagerMail,
//                    hrMail, cflLocation, joiningDate, sscResult, hscResult,
//                    underGraduateResult, postGraduateResult, collegeName, collegeBranch,
//                    technicalSkills, nonTechnicalSkills
//            );
//            return ResponseEntity.ok(cfl);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File processing error");
//        }
//    }


    @PostMapping("/create")
    public ResponseEntity<?> createCfl(@RequestBody Cfl cfl) {
       Cfl cflObj=cflService.createCfl(cfl);
       if(cflObj !=null){
           return ResponseEntity.ok(cflObj);
       }
       else{
           return ResponseEntity.status(400).body("Failed to create Cfl");
       }
    }

    @PostMapping("/createAll")
    public ResponseEntity<?> createListOfCfl(@RequestBody List<Cfl> cfl) {
        List<Cfl> cflList=cflService.createListOfCfl(cfl);
        if(!cflList.isEmpty()){
            return ResponseEntity.ok(cflList);
        }
        else{
            return ResponseEntity.status(400).body("Failed to create list of Cfl");
        }
    }


    @GetMapping("/getCflInfoDuringLogin/{cflEmail}")
    public ResponseEntity<?>getCflInfoDuringLogin(@PathVariable String cflEmail){
        Cfl cfl=cflService.getCflByEmailDuringLogin(cflEmail);
        if(cfl!=null){
            return ResponseEntity.ok(cfl);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found with this login");
        }
    }

    @GetMapping("/getAllByYear/{year}")
    public ResponseEntity<?> getAllByYear(@PathVariable String year) {
        List<Cfl> allCfl = cflService.getAllByYear(year);
        if (!allCfl.isEmpty()) {
            return ResponseEntity.ok(allCfl);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
        }
    }




    @PostMapping("/sendMail")
    public ResponseEntity<?> sentMailToMentor(@RequestParam Long empId,@RequestParam String email,@RequestParam String ccEmail,@RequestParam String subject, @RequestParam String message, @RequestParam String type) {
        Boolean result=cflService.sentMailToMentor(empId,email,ccEmail,subject, message ,type);

        if(result){
            return ResponseEntity.ok("Email sent successfully !!!");
        }
        else{
            return ResponseEntity.badRequest().body("Email Not sent !!!");
        }
    }


    @GetMapping("/getByMailHistoryByEmpId/{empId}")
    public ResponseEntity<?> getByMailHistoryByEmpId(@PathVariable Long empId) {
        List<MailHistory> mailHistoryList=cflService.getByMailHistoryByEmpId(empId);
        if(mailHistoryList !=null){
            return ResponseEntity.ok(mailHistoryList);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Mail History found for this Employee");
        }
    }


    @GetMapping("/getAllCflByManagerEmail/{managerEmail}")
    public ResponseEntity<?> getAllCflByManagerEmail(@PathVariable String managerEmail) {
        System.out.println(managerEmail+"managerEmail");
        List<Cfl> allCfl = cflService.getAllByManagerEmail(managerEmail);
        if (!allCfl.isEmpty()) {
            return ResponseEntity.ok(allCfl);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
        }
    }


    @GetMapping("/getAllCflByCflId/{empId}")
    public ResponseEntity<?> getCflByEmpId(@PathVariable Long empId) {
        Cfl perticularCfl = cflService.getCflByEmpId(empId);
        if (perticularCfl!=null) {
            return ResponseEntity.ok(perticularCfl);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
        }
    }

    //  Mentor Mentee Request ----------------------------------------------------

    // for sending mentor-mentee accept response By mentor
    @GetMapping("/accept/{cflEmail}")
    public String acceptRequest(@PathVariable String cflEmail) {
        Cfl cfl=cflService.getByCflEmail(cflEmail);
        return "E-Mail Request Is Accepted Successfully";
    }


    // for sending mentor-mentee extend response By mentor
    @GetMapping("/extend/{cflEmail}")
    public String declineRequest(@PathVariable String cflEmail) {
        Cfl cfl=cflService.getByCflDeclinedEmail(cflEmail);
        return "E-Mail Request Is Extend Successfully";
    }


    // Goal Setting Request -----------------------------------------------

    // for sending goal setting accept response By manager
    @GetMapping("/manager/accept/{cflEmail}")
    public String acceptGoalSettingRequest(@PathVariable String cflEmail) {
        cflService.sendMailFromManagerToCFLAndHr(cflEmail);
        return "Goal Setting Request Accepted Successfully";
    }


    // for sending goal setting extend response By manager
    @GetMapping("/manager/extend/{cflEmail}")
    public String extendGoalSettingRequest(@PathVariable String cflEmail) {
        cflService.sendExtendMailFromManagerToCFLAndHr(cflEmail);
        return "Goal Setting Request Extended Successfully";
    }


    // Probation Request -----------------------------------------------

    // for sending probation accept response By manager
    @GetMapping("/manager/probation/accept/{cflEmail}")
    public String acceptProbationRequest(@PathVariable String cflEmail) {
        cflService.sendProbationMailFromManagerToCFLAndHr(cflEmail);
        return "Probation End Request Accepted Successfully";
    }


    // for sending probation extend response to manager email
    @GetMapping("/manager/probation/extend/{cflEmail}")
    public String extendProbationRequest(@PathVariable String cflEmail) {
        cflService.sendProbationExtendMailFromManagerToCFLAndHr(cflEmail);
        return "Probation End Request Extended Successfully";
    }


    // -----------------------------------------------------

    @GetMapping("/getParticularCflByEmpId/{empId}")
    public ResponseEntity<?> getParticularCflByEmpId(@PathVariable Long empId) {
       Cfl cfl=cflService.getParticularCflByEmpId(empId);
       if(cfl!=null){
          return ResponseEntity.ok(cfl);
       }
       else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
       }
    }


    // ----------- upload file



    @PostMapping("/uploadCflFile")
    public ResponseEntity<?> uploadCflImage(@RequestParam Long empId, @RequestPart MultipartFile file) throws IOException {
        Cfl cfl=cflService.uploadCflImage(empId,file);
       if(cfl!=null){
           return ResponseEntity.ok(cfl);
       }
       else{
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
       }
    }


    // get All Cfl
    @GetMapping("/getAllCfl")
    public ResponseEntity<?>getAllCfl(){
        List<Cfl> cflList=cflService.getAllCfl();
        if(!cflList.isEmpty()){
            return ResponseEntity.ok(cflList);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cfl found");
        }
    }
}
