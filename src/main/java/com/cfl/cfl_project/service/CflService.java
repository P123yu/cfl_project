package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.Cfl;
import com.cfl.cfl_project.model.MailHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface CflService {

//    Cfl createCfl(Long empId,
//                  String cflFirstName,String cflMiddleName, String cflLastName, String cflEmail,
//                  String cflDepartment, String cflDesignation, String reportingManager, String reportingManagerMail,
//                  String hrMail, String cflLocation, String joiningDate, String sscResult,
//                  String hscResult, String underGraduateResult, String postGraduateResult,
//                  String collegeName, String collegeBranch, String technicalSkills,
//                  String nonTechnicalSkills) throws IOException;

    List<Cfl>getAllCfl();

    Cfl uploadCflImage(Long empId,MultipartFile file) throws IOException;

    Cfl getParticularCflByEmpId(Long empId);

    Cfl createCfl(Cfl cfl);

    List<Cfl> createListOfCfl(List<Cfl> list);

    List<Cfl> getAllByYear(String year);



    Boolean sentMailToMentor(Long empId, String email,String ccEmail,String subject,String message,String type);

    List<MailHistory>getByMailHistoryByEmpId(Long empId);

    Cfl getByCflEmail(String cflEmail);

    Cfl getByCflDeclinedEmail(String cflEmail);

    // list of cfl based on manager email
    List<Cfl> getAllByManagerEmail(String managerEmail);
    Cfl getCflByEmpId(Long empId);

    // fetch cfl data while cfl login via email
    Cfl getCflByEmailDuringLogin(String cflEmail);


    // Goal Setting Request ----------------------------------------------
//    // send automate mail request to manager
    void sendMailToManagerRegardingGoalSetting();

    // send automate accept mail request to manager
    void sendMailFromManagerToCFLAndHr(String cflEmail);

    // send automate extend mail request to manager
    void sendExtendMailFromManagerToCFLAndHr(String cflEmail);


    // probation Request ----------------------------------------------------

    // send automate probation mail request to manager
    void sendMailToManagerRegardingProbation();

    // send automate probation accept mail request to manager
    void sendProbationMailFromManagerToCFLAndHr(String cflEmail);

    // send automate probation extend mail request to manager
    void sendProbationExtendMailFromManagerToCFLAndHr(String cflEmail);

//    // count cfl
//    void sendManagerWiseCflCount(Boolean status);
}
