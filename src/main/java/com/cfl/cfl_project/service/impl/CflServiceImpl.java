package com.cfl.cfl_project.service.impl;
//
//import com.cfl.cfl_project.email.CflToMentorMail;
import com.cfl.cfl_project.model.Cfl;
import com.cfl.cfl_project.model.MailHistory;
import com.cfl.cfl_project.repository.CflRepository;
import com.cfl.cfl_project.repository.MailHistoryRepository;
import com.cfl.cfl_project.service.CflService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CflServiceImpl implements CflService {

    @Autowired
    private CflRepository cflRepository;

    @Override
    public Cfl createCfl(Long empId, String cflFirstName, String cflMiddleName, String cflLastName, String cflEmail, String cflDepartment, String cflDesignation, String reportingManager, String cflLocation, String joiningDate, String sscResult, String hscResult, String underGraduateResult, String postGraduateResult, String collegeName, String collegeBranch, String technicalSkills, String nonTechnicalSkills, MultipartFile file) throws IOException {
        Cfl cfl = new Cfl();
        cfl.setEmpId(empId);
        cfl.setCflFirstName(cflFirstName);
        cfl.setCflMiddleName(cflMiddleName);
        cfl.setCflLastName(cflLastName);
        cfl.setCflEmail(cflEmail);
        cfl.setCflDepartment(cflDepartment);
        cfl.setCflDesignation(cflDesignation);
        cfl.setReportingManager(reportingManager);
        cfl.setCflLocation(cflLocation);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate joiningDateParsed = LocalDate.parse(joiningDate, formatter);
        cfl.setJoiningDate(joiningDateParsed);
        cfl.setYear(joiningDateParsed.getYear() + "");
        System.out.println(joiningDate.substring(0,4)+"joiningDate.substring(0,4)");
        cfl.setSscResult(sscResult);
        cfl.setHscResult(hscResult);
        cfl.setUnderGraduateResult(underGraduateResult);
        cfl.setPostGraduateResult(postGraduateResult);
        cfl.setCollegeName(collegeName);
        cfl.setCollegeBranch(collegeBranch);
        cfl.setTechnicalSkills(technicalSkills);
        cfl.setNonTechnicalSkills(nonTechnicalSkills);

        if (file != null && !file.isEmpty()) {
            cfl.setFileName(file.getOriginalFilename());
            cfl.setFileData(file.getBytes());
        }

        return cflRepository.save(cfl);
    }

//    @Override
//    public Cfl getCflByEmpId(Long empId) {
//        return cflRepository.findByEmpId(empId);
//    }
//
//    @Override
//    public List<Cfl> getAllCfl() {
//        return cflRepository.findAll();
//    }
//
//    @Override
//    public Cfl updateCflById(Long id, String cflFirstName, String cflMiddleName, String cflLastName, String cflEmail, String cflDepartment, String cflDesignation, String reportingManager, String cflLocation, LocalDate joiningDate, String year, String sscResult, String hscResult, String underGraduateResult, String postGraduateResult, String collegeName, String collegeBranch, String technicalSkills, String nonTechnicalSkills, MultipartFile file) {
//        return cflRepository.findById(id).map(cfl -> {
//            cfl.setCflFirstName(cflFirstName);
//            cfl.setCflMiddleName(cflMiddleName);
//            cfl.setCflLastName(cflLastName);
//            cfl.setCflEmail(cflEmail);
//            cfl.setCflDepartment(cflDepartment);
//            cfl.setCflDesignation(cflDesignation);
//            cfl.setReportingManager(reportingManager);
//            cfl.setCflLocation(cflLocation);
//            cfl.setJoiningDate(joiningDate);
//            cfl.setYear(year);
//            cfl.setSscResult(sscResult);
//            cfl.setHscResult(hscResult);
//            cfl.setUnderGraduateResult(underGraduateResult);
//            cfl.setPostGraduateResult(postGraduateResult);
//            cfl.setCollegeName(collegeName);
//            cfl.setCollegeBranch(collegeBranch);
//            cfl.setTechnicalSkills(technicalSkills);
//            cfl.setNonTechnicalSkills(nonTechnicalSkills);
//
//            if (file != null && !file.isEmpty()) {
//                cfl.setFileName(file.getOriginalFilename());
//                try {
//                    cfl.setFileData(file.getBytes());
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//            return cflRepository.save(cfl);
//        }).orElse(null);
//    }
//
//    @Override
//    public void deleteCflById(Long id) {
//        if (cflRepository.existsById(id)) {
//            cflRepository.deleteById(id);
//        }
//    }
//
//    @Override
//    public List<Cfl> getAllByYear(String year) {
//        return cflRepository.findByYear(year);
//    }
//
//
//    @Autowired
//    private CflToMentorMail cflToMentorMail;
//
//    @Autowired
//    private MailHistoryRepository mailHistoryRepository;
//
//
//
//    @Async
//    @Override
//    public Boolean sentMailToMentor(Long empId, String mentorEmail,String subject,String message) {
//        Cfl cfl=cflRepository.findByEmpId(empId);
//        Long cflId=cfl.getEmpId();
//        String year=cfl.getYear();
//        String cflName=cfl.getCflFirstName()+"_"+cfl.getCflLastName();
//      //  String mentorName=cfl.getMentorName();
//        String cflEmail=cfl.getCflEmail();
//      //  String body = mentorName + ",\n\n" + message;
//
//        // mail history
//        MailHistory mailHistory=new MailHistory();
//        mailHistory.setEmpId(empId);
//        LocalDate date=LocalDate.now();
//        mailHistory.setMailDate(date);
//
//        LocalTime time = LocalTime.now();
//        mailHistory.setMailTime(time);
//
//        mailHistoryRepository.save(mailHistory);
//       // return cflToMentorMail.sendEmail(cflId,cflName,cflEmail,mentorEmail,subject,body,year);
//        return true;
//    }
//
//    @Override
//    public List<MailHistory> getByMailHistoryByEmpId(Long empId) {
//        return mailHistoryRepository.findByEmpId(empId);
//    }
//
//    @Override
//    public List<Cfl> createMentor(Long mentorId,String empId, String mentorName, String mentorEmail, String mentorDepartment, String mentorLocation, String mentorDesignation, MultipartFile mentorFile) {
//            List<Cfl>cflMentorList=new ArrayList<>();
//             String [] arrCfl=empId.split(",");
//             for(String cflId:arrCfl){
//                 Cfl cflInfoFromDB=cflRepository.findByEmpId(Long.parseLong(cflId));
//                 System.out.println(cflInfoFromDB+"cflInfoFromDB");
//                 if(cflInfoFromDB !=null){
//                     cflInfoFromDB.setEmpId(Long.parseLong(cflId));
////                     cflInfoFromDB.setMentorId(mentorId);
////                     cflInfoFromDB.setMentorName(mentorName);
////                     cflInfoFromDB.setMentorEmail(mentorEmail);
////                     cflInfoFromDB.setMentorDepartment(mentorDepartment);
////                     cflInfoFromDB.setMentorLocation(mentorLocation);
////                     cflInfoFromDB.setMentorDesignation(mentorDesignation);
//
//                     // file
//
//                     if (mentorFile!= null && !mentorFile.isEmpty()) {
////                         cflInfoFromDB.setMentorFileName(mentorFile.getOriginalFilename());
////                         try {
////
//////                             cflInfoFromDB.setMentorFileData(mentorFile.getBytes());
////                         } catch (IOException e) {
////                             throw new RuntimeException(e);
////                         }
//                     }
//                 }
//                 cflMentorList.add(cflInfoFromDB);
//
//             }
//        return cflRepository.saveAll(cflMentorList);
//    }
//
//    @Override
//    public List<Cfl> getByMentorId(Long mentorId) {
//        return cflRepository.findByMentorId(mentorId);
//    }
//
//    @Override
//    public Boolean sendLinkToMultipleRecipients(String recipientEmails, String subject, String link) {
//        List<String>newList=new ArrayList<String>();
//        String []listOfEmail=recipientEmails.split(",");
//        for(String i:listOfEmail){
//            newList.add(i);
//        }
//        System.out.println(newList+"newList///");
//        return cflToMentorMail.sendLinkToMultipleRecipients(newList,subject,link);
//    }
}
