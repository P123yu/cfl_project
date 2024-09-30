package com.cfl.cfl_project.email;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Configuration
@Component
public class CflToMentorMail {

    @Value("${mail.host}")
    private String mailHost;

    @Value("${mail.port}")
    private int mailPort;

    @Value("${mail.username}")
    private String mailUsername;

    @Value("${mail.password}")
    private String mailPassword;

    private final TemplateEngine templateEngine;


    public CflToMentorMail(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);
        mailSender.setUsername(mailUsername);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        return mailSender;
    }


    // mentee to mentor
    public boolean sendEmail(String cflName,String cflEmail,String cflDesignation,String cflDepartment,String cflLocation,String reportingManager,String email, String subject, String body,String year,String ccEmail,String type) {
        boolean isSent = false;
        Context context = new Context();
        context.setVariable("body", body);
        context.setVariable("cflName", cflName);
        context.setVariable("cflEmail", cflEmail);
        System.out.println(cflEmail+"cflEmail......");
        context.setVariable("cflDesignation", cflDesignation);
        context.setVariable("cflDepartment", cflDepartment);
        context.setVariable("cflLocation", cflLocation);
        context.setVariable("reportingManager", reportingManager);
        context.setVariable("type", type);
        LocalDate date=LocalDate.now();
        context.setVariable("year",  date.getYear());
        context.setVariable("link", "https://calendly.com/cmsfutureleaders/cms-future-leaders");

        String process="";
        if(type.equals("Mentor")) {
            process = templateEngine.process("CflToMentorMailTemplate.html", context);
        }
        else{
            process = templateEngine.process("CflToGeneralMailTemplate.html", context);
        }

        try {
            MimeMessage mimeMessage = javaMailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(process, true);

            // cc
            String []ccList=ccEmail.split(",");
            helper.setCc(ccList);
            javaMailSender().send(mimeMessage);
            isSent = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSent;
    }


    // from mentor to mentee

    public boolean sendMailFromMentorToMentee(String cflEmail,String mentorName, String cflName,String subject, String message, String status) {
        boolean isSent = true;

        // Set up the email content
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("cflEmail",cflEmail);
        System.out.println(cflEmail+"cflEmail in mentor");
        context.setVariable("mentorName",mentorName);
        context.setVariable("cflName",cflName);

        String process = templateEngine.process("MentorToMenteeEmailTemplate.html", context);

        try {
            MimeMessage mimeMessage = javaMailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(cflEmail);
            helper.setSubject(subject);
            helper.setText(process, true);

            javaMailSender().send(mimeMessage);
            isSent = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSent = false;
        }

        return isSent;
    }



    public boolean sendGoalSetting(String recipientEmail, String subject, String message) {
        boolean isSent = true;

        // Set up the email content
        Context context = new Context();
        context.setVariable("message", message);

        String process = templateEngine.process("MentorToMenteeMailResponse.html", context);

        try {
            MimeMessage mimeMessage = javaMailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            helper.setText(process, true);


            javaMailSender().send(mimeMessage);
            isSent = true;
        } catch (Exception e) {
            e.printStackTrace();
            isSent = false;
        }

        return isSent;
    }



    public void sendGoalSettingToManager(String managerEmail, String cflName, String subject,String cflEmail,String hrEmail) {
        System.out.println(managerEmail + "gg " + cflName + " " + subject + " ");

        // Set up the email content
        Context context = new Context();
        context.setVariable("cflName", cflName);
        context.setVariable("subject", subject);
        context.setVariable("cflEmail", cflEmail);
        context.setVariable("hrEmail", hrEmail);
        context.setVariable("link", "https://calendly.com/cmsfutureleaders/cms-future-leaders");

        String process = templateEngine.process("CflGoalSettingEmailTemplate.html", context);

        try {
            MimeMessage mimeMessage = javaMailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(managerEmail);
            helper.setSubject(subject);
            helper.setText(process, true);

            javaMailSender().send(mimeMessage);
            System.out.println("Email successfully sent to " + managerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to send email to " + managerEmail + ": " + e.getMessage());
        }
    }



    public void sendProbationStatusToManager(String managerEmail, String cflName, String subject,String cflEmail,String hrEmail) {
        // Set up the email content
        Context context = new Context();
        context.setVariable("cflName", cflName);
        context.setVariable("subject", subject);
        context.setVariable("cflEmail", cflEmail);
        context.setVariable("hrEmail", hrEmail);
        context.setVariable("link", "https://calendly.com/cmsfutureleaders/cms-future-leaders");

        String process = templateEngine.process("ProbationConfirmationToManager", context);

        try {
            MimeMessage mimeMessage = javaMailSender().createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setTo(managerEmail);
            helper.setSubject(subject);
            helper.setText(process, true);


            javaMailSender().send(mimeMessage);
            System.out.println("Email successfully sent to " + managerEmail);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to send email to " + managerEmail + ": " + e.getMessage());
        }
    }




    // ==================== GOAL SETTING MAIL ==============================

    // accept goal setting ------------------------------------------------

    public void sendGoalSettingToCflAndHr(String managerName, String managerEmail, String cflName, String cflEmail, String hrEmail, LocalDate date) {

        // Set up common variables for both emails
        Context context = new Context();
        context.setVariable("cflName", cflName);
        context.setVariable("cflEmail", cflEmail);
        context.setVariable("managerName", managerName);
        context.setVariable("date", date);
        context.setVariable("managerEmail", managerEmail);
        context.setVariable("link", "https://calendly.com/cmsfutureleaders/cms-future-leaders");

        try {
            // Send email to CFL using the first template
            String cflEmailContent = templateEngine.process("ManagerToCflGoalSettingEmailTemplate.html", context);
            MimeMessage mimeMessageCfl = javaMailSender().createMimeMessage();
            MimeMessageHelper helperCfl = new MimeMessageHelper(mimeMessageCfl, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperCfl.setTo(cflEmail);
            helperCfl.setSubject("Response From Manager");
            helperCfl.setText(cflEmailContent, true);
            javaMailSender().send(mimeMessageCfl);


//            // for scheduling calendly
//            String managerEmailContent = templateEngine.process("CalenderSchedulingForManagerEmailTemplate.html", context);
//            MimeMessage mimeMessageManager = javaMailSender().createMimeMessage();
//            MimeMessageHelper helperManager= new MimeMessageHelper(mimeMessageCfl, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//            helperManager.setTo(managerEmail);
//            helperManager.setSubject("Schedule Event On Calendly");
//            helperManager.setText(managerEmailContent, true);
//            javaMailSender().send(mimeMessageCfl);

            // Modify the context or add specific variables for HR if needed
            context.setVariable("hrEmail", hrEmail);

            // Send email to HR using the second template
            String hrEmailContent = templateEngine.process("ManagerToHrGoalSettingEmailTemplate.html", context);
            MimeMessage mimeMessageHr = javaMailSender().createMimeMessage();
            MimeMessageHelper helperHr = new MimeMessageHelper(mimeMessageHr, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperHr.setTo(hrEmail);
            helperHr.setSubject("Response From Manager");
            helperHr.setText(hrEmailContent, true);
           // helperHr.setCc(managerName); // Optional, CC manager on HR's email if required
            javaMailSender().send(mimeMessageHr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // extend goal setting ----------------------------------------------

    public void sendGoalSettingToCflAndHr(String managerName, String cflName, String cflEmail, String hrEmail, LocalDate date) {

        // Set up common variables for both emails
        Context context = new Context();
        context.setVariable("cflName", cflName);
        context.setVariable("cflEmail", cflEmail);
        context.setVariable("managerName", managerName);
        context.setVariable("date", date);
        try {
            // Send email to CFL using the first template
            String cflEmailContent = templateEngine.process("ManagerToCflExtendGoalSettingEmailTemplate.html", context);
            MimeMessage mimeMessageCfl = javaMailSender().createMimeMessage();
            MimeMessageHelper helperCfl = new MimeMessageHelper(mimeMessageCfl, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperCfl.setTo(cflEmail);
            helperCfl.setSubject("Response From Manager");
            helperCfl.setText(cflEmailContent, true);
            javaMailSender().send(mimeMessageCfl);



            // Send email to HR using the second template
            String hrEmailContent = templateEngine.process("ManagerToHrExtendGoalSettingEmailTemplate.html", context);
            MimeMessage mimeMessageHr = javaMailSender().createMimeMessage();
            MimeMessageHelper helperHr = new MimeMessageHelper(mimeMessageHr, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperHr.setTo(hrEmail);
            helperHr.setSubject("Response From Manager");
            helperHr.setText(hrEmailContent, true);
            // helperHr.setCc(managerName); // Optional, CC manager on HR's email if required
            javaMailSender().send(mimeMessageHr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//     PROBATION REQUEST MAIL =====================================================


    // accept probation request ------------------------------------------------

    public void sendProbationToCflAndHr(String managerName,String cflName, String cflEmail, String hrEmail, LocalDate date) {

        // Set up common variables for both emails
        Context context = new Context();
        context.setVariable("cflName", cflName);
        context.setVariable("cflEmail", cflEmail);
        context.setVariable("managerName", managerName);
        context.setVariable("date", date);


        try {
            // For Manager To Cfl ---------------------------------------
            // Send email to CFL using the first template
            String cflEmailContent = templateEngine.process("ManagerToCflProbationEmailTemplate.html", context);
            MimeMessage mimeMessageCfl = javaMailSender().createMimeMessage();
            MimeMessageHelper helperCfl = new MimeMessageHelper(mimeMessageCfl, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperCfl.setTo(cflEmail);
            helperCfl.setSubject("Response From Manager");
            helperCfl.setText(cflEmailContent, true);
            javaMailSender().send(mimeMessageCfl);

            // For Manager To HR ---------------------------------------

            // Modify the context or add specific variables for HR if needed
            context.setVariable("hrEmail", hrEmail);

            // Send email to HR using the second template
            String hrEmailContent = templateEngine.process("ManagerToHrProbationEmailTemplate.html", context);
            MimeMessage mimeMessageHr = javaMailSender().createMimeMessage();
            MimeMessageHelper helperHr = new MimeMessageHelper(mimeMessageHr, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperHr.setTo(hrEmail);
            helperHr.setSubject("Response From Manager");
            helperHr.setText(hrEmailContent, true);
            // helperHr.setCc(managerName); // Optional, CC manager on HR's email if required
            javaMailSender().send(mimeMessageHr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // extend goal setting ----------------------------------------------

    public void sendProbationExtendToCflAndHr(String managerName, String cflName, String cflEmail, String hrEmail, LocalDate date) {

        // Set up common variables for both emails
        Context context = new Context();
        context.setVariable("cflName", cflName);
        context.setVariable("cflEmail", cflEmail);
        context.setVariable("managerName", managerName);
        context.setVariable("date", date);
        try {
            // Send email to CFL using the first template
            String cflEmailContent = templateEngine.process("ManagerToCflProbationExtendEmailTemplate.html", context);
            MimeMessage mimeMessageCfl = javaMailSender().createMimeMessage();
            MimeMessageHelper helperCfl = new MimeMessageHelper(mimeMessageCfl, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperCfl.setTo(cflEmail);
            helperCfl.setSubject("Response From Manager");
            helperCfl.setText(cflEmailContent, true);
            javaMailSender().send(mimeMessageCfl);

            // Send email to HR using the second template
            String hrEmailContent = templateEngine.process("ManagerToHrProbationExtendEmailTemplate.html", context);
            MimeMessage mimeMessageHr = javaMailSender().createMimeMessage();
            MimeMessageHelper helperHr = new MimeMessageHelper(mimeMessageHr, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helperHr.setTo(hrEmail);
            helperHr.setSubject("Response From Manager");
            helperHr.setText(hrEmailContent, true);
            // helperHr.setCc(managerName); // Optional, CC manager on HR's email if required
            javaMailSender().send(mimeMessageHr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//
//    // send mentoring logbook
//    public void sendMentoringLogBook(List<String> listOfEmails) {
//        // The primary recipient
//        String primaryRecipient = "piyushsinghlkr2002@gmail.com";
//
//        // Prepare the Thymeleaf context
//        Context context = new Context();
//
//        try {
//            // Process the HTML template
//            String cflEmailContent = templateEngine.process("MentoringLogBookToAllCflEmailTemplate.html", context);
//
//            // Create a MIME message
//            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//
//            // Set the primary recipient
//            helper.setTo(primaryRecipient);
//
//            // Add the list of emails as CC (all emails in the list)
//            if (!listOfEmails.isEmpty()) {
//                helper.setCc(listOfEmails.toArray(new String[0]));
//            }
//
//            // Set the email subject and content
//            helper.setSubject("Upload Mentoring LogBook");
//            helper.setText(cflEmailContent, true); // true indicates that the content is HTML
//
//            // Send the email
//            javaMailSender.send(mimeMessage);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//


    public void sendMentoringLogBook(List<String> listOfEmails) {

        // Set up common variables for both emails
        Context context = new Context();
        LocalDate date=LocalDate.now();
        context.setVariable("date", date);
        try {
            // Send email to CFL using the first template
            String cflEmailContent = templateEngine.process("MentoringLogBookToAllCflEmailTemplate.html", context);
            MimeMessage mimeMessageCfl = javaMailSender().createMimeMessage();
            MimeMessageHelper helperCfl = new MimeMessageHelper(mimeMessageCfl, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helperCfl.setTo(listOfEmails.remove(0));
            helperCfl.setSubject("Response From Manager");
            helperCfl.setText(cflEmailContent, true);
            // cc

            String []ccList=listOfEmails.stream().toArray(String[]::new);
            helperCfl.setCc(ccList);
            javaMailSender().send(mimeMessageCfl);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
