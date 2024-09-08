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

    Cfl createCfl(Long empId,
                  String cflFirstName,
                  String cflMiddleName,
                  String cflLastName,
                  String cflEmail,
                  String cflDepartment,
                  String cflDesignation,
                  String reportingManager,
                  String cflLocation,
                  String joiningDate,
                  String sscResult,
                  String hscResult,
                  String underGraduateResult,
                  String postGraduateResult,
                  String collegeName,
                  String collegeBranch,
                  String technicalSkills,
                  String nonTechnicalSkills,
                  MultipartFile file) throws IOException;

//    Cfl getCflByEmpId(Long empId);
//
//    List<Cfl> getAllCfl();
//
//    Cfl updateCflById(Long id,
//                      String cflFirstName,
//                      String cflMiddleName,
//                      String cflLastName,
//                      String cflEmail,
//                      String cflDepartment,
//                      String cflDesignation,
//                      String reportingManager,
//                      String cflLocation,
//                      LocalDate joiningDate,
//                      String year,
//                      String sscResult,
//                      String hscResult,
//                      String underGraduateResult,
//                      String postGraduateResult,
//                      String collegeName,
//                      String collegeBranch,
//                      String technicalSkills,
//                      String nonTechnicalSkills,
//                      MultipartFile file);
//
//    void deleteCflById(Long id);
//
//    List<Cfl> getAllByYear(String year);
//
//    Boolean sentMailToMentor(Long empId, String mentorEmail,String subject,String message);
//
//    List<MailHistory>getByMailHistoryByEmpId(Long empId);
//
//
//
//    List<Cfl> createMentor(Long mentorId,
//                     String empId,
//                  String mentorName,
//                  String mentorEmail,
//                  String mentorDepartment,
//                  String mentorLocation,
//                  String mentorDesignation, MultipartFile mentorFile);
//
//
//    List<Cfl> getByMentorId(Long mentorId);
//
//    Boolean sendLinkToMultipleRecipients(String recipientEmails, String subject, String link) ;
//
//
//

    }
