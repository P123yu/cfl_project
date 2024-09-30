
package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.*;
import com.cfl.cfl_project.repository.*;
import com.cfl.cfl_project.service.CflService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.cfl.cfl_project.email.CflToMentorMail;

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
    public List<Cfl> getAllCfl() {
        return cflRepository.findAll();
    }

    @Override
    public Cfl uploadCflImage(Long empId, MultipartFile file) throws IOException {
        Cfl cfl=cflRepository.findByEmpId(empId);
        cfl.setFileName(file.getOriginalFilename());
        cfl.setFileData(file.getBytes());
        Cfl cflFile=cflRepository.save(cfl);
        return cflFile;
    }

    @Override
    public Cfl getParticularCflByEmpId(Long empId) {
        return cflRepository.findByEmpId(empId);
    }


    @Override
    public Cfl createCfl(Cfl cfl) {
        cfl.setGoalSettingStatusHrToMgr(true);
        cfl.setProbationStatus(true);
        return cflRepository.save(cfl);
    }

    @Override
    public List<Cfl> createListOfCfl(List<Cfl> list) {
        List<Cfl> savedCFLs = new ArrayList<>();
        for (Cfl cfl : list) {
            cfl.setGoalSettingStatusHrToMgr(true);
            cfl.setProbationStatus(true);
            Cfl savedCfl = createCfl(cfl);
            savedCFLs.add(savedCfl);
        }
        return cflRepository.saveAll(savedCFLs);
    }


    @Override
    public List<Cfl> getAllByYear(String year) {
        return cflRepository.findByYear(year);
    }




    @Autowired
    private CflToMentorMail cflToMentorMail;

    @Autowired
    private MailHistoryRepository mailHistoryRepository;

    @Autowired
    private MentorRepository mentorRepository;


    @Async
    @Override
    public Boolean sentMailToMentor(Long empId, String email,String ccEmail,String subject,String message,String type) {
        System.out.println(empId+"empId email");
        Cfl cfl=cflRepository.findByEmpId(empId);
        String cflDesignation=cfl.getCflDesignation();
        String cflDepartment=cfl.getCflDepartment();
        String cflLocation=cfl.getCflLocation();
        String reportingManager=cfl.getReportingManager();
        String year=cfl.getYear();
        String cflName=cfl.getCflFirstName()+"_"+cfl.getCflLastName();

        String cflEmail=cfl.getCflEmail();
//        String body = mentorName + ",\n\n" + message;
        String body =message;

        // mail history  ----------------------------------------------
        MailHistory mailHistory=new MailHistory();
        mailHistory.setEmpId(empId);
        LocalDate date=LocalDate.now();
        mailHistory.setMailDate(date);
        LocalTime time = LocalTime.now();
        mailHistory.setMailTime(time);
        mailHistoryRepository.save(mailHistory);
        return cflToMentorMail.sendEmail(cflName,cflEmail,cflDesignation,cflDepartment,cflLocation,reportingManager,email,subject,body,year,ccEmail,type);
    }

    @Override
    public List<MailHistory> getByMailHistoryByEmpId(Long empId) {
        return mailHistoryRepository.findByEmpId(empId);
    }

    @Override
    public List<Cfl> getAllByManagerEmail(String managerEmail) {
        return cflRepository.findByReportingManagerMail(managerEmail);
    }

    @Override
    public Cfl getCflByEmpId(Long empId) {
        return cflRepository.findByEmpId(empId);
    }

    @Override
    public Cfl getCflByEmailDuringLogin(String cflEmail) {
        return cflRepository.findByCflEmail(cflEmail);
    }

    // mentor-mentee requests =====================================================

    @Override
    public Cfl getByCflEmail(String cflEmail) {
        // Retrieve the Cfl entity based on the email
        Cfl cfl = cflRepository.findByCflEmail(cflEmail);
        System.out.println(String.valueOf(cfl)+"cfl");

        if (cfl != null) {
            // Retrieve and process the current acceptance value
            String currentAcceptance = cfl.getEmailAcceptance();
            if (currentAcceptance == null || currentAcceptance.isEmpty()) {
                currentAcceptance = "";
            }
            // Append "accepted" with proper comma separation
            if (currentAcceptance.isEmpty()) {
                currentAcceptance = "a";
            } else {
                currentAcceptance += ",a";
            }

            // Set the updated email acceptance value
            cfl.setEmailAcceptance(currentAcceptance);
            String subject1="E-mail Response From Mentor To Mentee";
            String message1="Congratulation's Your Mentoring Request Is Accepted By Your Mentor";
            String status="accept";

            Long mentorId=cfl.getMentorId();
            String cflName= cfl.getCflFirstName();
            String mentorName=mentorRepository.findByMentorId(mentorId).getMentorName();
            cflToMentorMail.sendMailFromMentorToMentee(cflEmail,mentorName,cflName,subject1,message1,status);

            return cflRepository.save(cfl);
        }
        return null;
    }



    // ---- mentor extend the mentor-mentee request ------------------------

    @Override
    public Cfl getByCflDeclinedEmail(String cflEmail) {
        Cfl cfl = cflRepository.findByCflEmail(cflEmail);
        if (cfl != null) {
            String currentDeclined = cfl.getEmailDeclined();
            if (currentDeclined == null || currentDeclined.isEmpty()) {
                currentDeclined = "";
            }
            // Append "accepted" with proper comma separation
            if (currentDeclined.isEmpty()) {
                currentDeclined = "p";
            } else {
                currentDeclined += ",p";
            }
            cfl.setEmailDeclined(currentDeclined);
            String subject="E-mail Response From Mentor To Mentee";
            String message="Oops !!! E-mail Request Is PostPone By Your Mentor";
            String status="postpone";
            Long mentorId=cfl.getMentorId();
            String cflName= cfl.getCflFirstName();
            String mentorName=mentorRepository.findByMentorId(mentorId).getMentorName();
            cflToMentorMail.sendMailFromMentorToMentee(cflEmail,mentorName,cflName,subject,message,status);
            return cflRepository.save(cfl);
        }
        return null;
    }



    // ============================GOAL SETTING========================================

    // automatically generated email for goal setting to manager

    @Autowired
    private GoalSettingTrackerRepository goalSettingTrackerRepository;

    // for setting quarter auto
    public static String getQuarter() {
        LocalDate date=LocalDate.now();
        int month = date.getMonthValue();

        if (month >= 1 && month <= 3) {
            return "Q4";
        } else if (month >= 4 && month <= 6) {
            return "Q1";
        } else if (month >= 7 && month <= 9) {
            return "Q2";
        } else if (month >= 10 && month <= 12) {
            return "Q3";
        } else {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }








//    @Scheduled(cron = "0 * * * * ?") // Run every minute
@Scheduled(cron = "0 0 10 17 12 ?", zone = "UTC") // For December 17th
@Scheduled(cron = "0 0 10 17 3 ?", zone = "UTC")  // For March 17th
@Scheduled(cron = "0 0 10 16 6 ?", zone = "UTC")  // For June 16th
@Scheduled(cron = "0 0 10 16 9 ?", zone = "UTC")  // For September 16th

@Transactional
    public void sendMailToManagerRegardingGoalSettingEveryQuarter() {
        List<Cfl> cflGoalSettingStatus = cflRepository.findByGoalSettingStatusHrToMgr(false);
        List<Cfl> cflGoalSettings = new ArrayList<>();
        for (Cfl cflStatus : cflGoalSettingStatus) {
            cflStatus.setGoalSettingStatusHrToMgr(true);
            cflStatus.setCflMgrQuarterStatus(true);
            cflGoalSettings.add(cflStatus);
        }
        cflRepository.saveAll(cflGoalSettings);
    }


    @Override
   // @Scheduled(cron = "0 * * * * ?") // Run every minute
    @Scheduled(cron = "0 */10 * * * ?")
    public void sendMailToManagerRegardingGoalSetting() {
        List<Cfl> allUpdatedStatus = new ArrayList<>();
        List<GoalSettingTracker> goals = new ArrayList<>();
        List<Cfl> cflGoalSettingStatus = cflRepository.findByGoalSettingStatusHrToMgr(true) ;
        for (Cfl cflStatus : cflGoalSettingStatus) {
            String mgrEmail = cflStatus.getReportingManagerMail();
            String cflFirstName = cflStatus.getCflFirstName();
            String cflEmail = cflStatus.getCflEmail();
            String hrEmail=cflStatus.getHrMail();
            if (mgrEmail != null && !mgrEmail.isEmpty()) {
                // Send individual email for each manager and their respective CFL
                String subject = "Regarding Goal Setting With CFL";
                cflToMentorMail.sendGoalSettingToManager(mgrEmail, cflFirstName, subject,cflEmail,hrEmail);
            }
            // Update goal setting status to false after sending the email
            Cfl statusUpdate = cflRepository.findByCflEmail(cflEmail);
            statusUpdate.setGoalSettingStatusHrToMgr(false);

            // goal setting tracker

            GoalSettingTracker goalSettingTracker=new GoalSettingTracker();
            goalSettingTracker.setCflId(cflStatus.getEmpId());
            goalSettingTracker.setGoalInitiatedFromHrToManager(true);
            goalSettingTracker.setQuarter(getQuarter());
            goals.add(goalSettingTracker);
            allUpdatedStatus.add(statusUpdate);
        }
        // Save all updated CFLs with status changes
        cflRepository.saveAll(allUpdatedStatus);
        goalSettingTrackerRepository.saveAll(goals);
    }



    @Autowired
    private ManagerRepository managerRepository;


    // manager accepted goal setting response to hr and cfl  -----------

    @Override
    public void sendMailFromManagerToCFLAndHr(String cflEmail) {
        Cfl cfl=cflRepository.findByCflEmail(cflEmail);
        String managerEmail = cfl.getReportingManagerMail();
        String managerName=managerRepository.findByManagerEmail(managerEmail).getManagerName();
        String cflName = cfl.getCflFirstName();
        String hrMail = cfl.getHrMail();
        LocalDate date = LocalDate.now();

        System.out.println(managerName+"managerName");

        //  set false if manager accept the goal setting request
        cfl.setCflMgrQuarterStatus(false);
        cflRepository.save(cfl);

        // goal setting tracker
        GoalSettingTracker goalSettingTracker=goalSettingTrackerRepository.findByCflIdAndQuarter(cfl.getEmpId(),getQuarter());
        goalSettingTracker.setResponseSendByManagerToCfl(true);
        goalSettingTracker.setResponseSendByManagerToHr(true);
        goalSettingTrackerRepository.save(goalSettingTracker);


        cflToMentorMail.sendGoalSettingToCflAndHr(managerName,managerEmail,cflName,cflEmail,hrMail,date);
    }



    //  manager extended goal setting response to hr and cfl -----------------------
    @Override
    public void sendExtendMailFromManagerToCFLAndHr(String cflEmail) {
        Cfl cfl=cflRepository.findByCflEmail(cflEmail);
        String managerEmail = cfl.getReportingManagerMail();
        String managerName=managerRepository.findByManagerEmail(managerEmail).getManagerName();
        String cflName = cfl.getCflFirstName();
        String hrMail = cfl.getHrMail();
        LocalDate date = LocalDate.now();
        cflToMentorMail.sendGoalSettingToCflAndHr(managerName,cflName,cflEmail,hrMail,date);
    }




    // =============================PROBATION============================================

    @Autowired
    private ProbationTrackerRepository probationTrackerRepository;

    // automatically generated email for probation to manager
    @Override
    @Scheduled(cron = "0 0 0 15 12 ?")
    //@Scheduled(cron = "0 * * * * ?")
    public void sendMailToManagerRegardingProbation() {
        List<Cfl> allUpdatedStatus = new ArrayList<>();
        List<ProbationTracker> probation = new ArrayList<>();
        List<Cfl> cflGoalSettingStatus = cflRepository.findByProbationStatus(true);
        for (Cfl cflStatus : cflGoalSettingStatus) {
            String mgrEmail = cflStatus.getReportingManagerMail();
            String cflFirstName = cflStatus.getCflFirstName();
            String cflEmail = cflStatus.getCflEmail();
            String hrEmail = cflStatus.getHrMail();
            if (mgrEmail != null && !mgrEmail.isEmpty()) {
                String subject = "Regarding Probation Period Confirmation";
                cflToMentorMail.sendProbationStatusToManager(mgrEmail, cflFirstName, subject, cflEmail, hrEmail);
            }
            Cfl statusUpdate = cflRepository.findByCflEmail(cflEmail);
            statusUpdate.setProbationStatus(false);

            // probation tracker

            ProbationTracker probationTracker=new ProbationTracker();
            probationTracker.setCflId(cflStatus.getEmpId());
            probationTracker.setProbationInitiatedFromHrToManager(true);
            probation.add(probationTracker);
            allUpdatedStatus.add(statusUpdate);
        }
        cflRepository.saveAll(allUpdatedStatus);
        probationTrackerRepository.saveAll(probation);
    }


    // manager accepted probation response to hr and cfl -----------------------
    @Override
    public void sendProbationMailFromManagerToCFLAndHr(String cflEmail) {
        Cfl cfl=cflRepository.findByCflEmail(cflEmail);
        String managerEmail = cfl.getReportingManagerMail();
        String managerName=managerRepository.findByManagerEmail(managerEmail).getManagerName();
        String cflName = cfl.getCflFirstName();
        String hrMail = cfl.getHrMail();
        LocalDate date = LocalDate.now();

        // probation tracker
        ProbationTracker probationTracker=probationTrackerRepository.findByCflId(cfl.getEmpId());
        probationTracker.setResponseSendByManagerToCfl(true);
        probationTracker.setResponseSendByManagerToHr(true);
        probationTrackerRepository.save(probationTracker);

        cflToMentorMail.sendProbationToCflAndHr(managerName,cflName,cflEmail,hrMail,date);
    }


    //  manager extended probation response to hr and cfl -----------------------
    @Override
    public void sendProbationExtendMailFromManagerToCFLAndHr(String cflEmail) {
        Cfl cfl=cflRepository.findByCflEmail(cflEmail);
        String managerEmail = cfl.getReportingManagerMail();
        String managerName=managerRepository.findByManagerEmail(managerEmail).getManagerName();
        String cflName = cfl.getCflFirstName();
        String hrMail = cfl.getHrMail();
        LocalDate date = LocalDate.now();
        cflToMentorMail.sendProbationExtendToCflAndHr(managerName,cflName,cflEmail,hrMail,date);
    }


    // trigger each mail to cfl after


  @Scheduled(cron = "0 0 0 1 * ?")
    public void sendMentoringLogBook(){
        LocalDate date=LocalDate.now();
        String year = String.valueOf(date.getYear());
        List<Cfl> currentYearCflList=cflRepository.findAllByYear(year);
        System.out.println(currentYearCflList);
        List<String>listOfEmails=new ArrayList<String>();
        for(Cfl cfl:currentYearCflList){
            String cflEmail=cfl.getCflEmail();
            listOfEmails.add(cflEmail);
        }
        cflToMentorMail.sendMentoringLogBook(listOfEmails);
    }


    // count total number of cfl comes under particular manager and send mail to respective manager


//    @Scheduled(cron = "0 * * * * ?")
//    public void sendManagerWiseCflCount(Boolean status){
//        cflRepository.
//    }

}

