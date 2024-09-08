//package com.cfl.cfl_project.email;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Properties;
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;
//
//@Configuration
//@Component
//public class CflToMentorMail {
//
//    @Value("${mail.host}")
//    private String mailHost;
//
//    @Value("${mail.port}")
//    private int mailPort;
//
//    @Value("${mail.username}")
//    private String mailUsername;
//
//    @Value("${mail.password}")
//    private String mailPassword;
//
//    private final TemplateEngine templateEngine;
//
//
//    public CflToMentorMail(TemplateEngine templateEngine) {
//        this.templateEngine = templateEngine;
//    }
//
//    @Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(mailHost);
//        mailSender.setPort(mailPort);
//        mailSender.setUsername(mailUsername);
//        mailSender.setPassword(mailPassword);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        return mailSender;
//    }
//
//
//
//    public boolean sendEmail(Long cflId,String cflName,String cflEmail,String mentorEmail, String subject, String body,String year) {
//        boolean isSent = false;
//
//        Context context = new Context();
//        context.setVariable("body", body);
//        context.setVariable("cflId", cflId);
//        context.setVariable("cflName", cflName);
//        context.setVariable("cflEmail", cflEmail);
//        context.setVariable("year", year);
//
//
//        String process = templateEngine.process("CflToMentorMailTemplate.html", context);
//
//        try {
//            MimeMessage mimeMessage = javaMailSender().createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//            helper.setTo(mentorEmail);
//            helper.setSubject(subject);
//            helper.setText(process, true);
//
//            javaMailSender().send(mimeMessage);
//            isSent = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return isSent;
//    }
//
//
//
//    public boolean sendLinkToMultipleRecipients(List<String> recipientEmails, String subject, String link) {
//        boolean allSent = true;
//
//        // Set up the email content
//        Context context = new Context();
//        context.setVariable("link", link);
//
//        String process = templateEngine.process("MultipleLinkEmailTemplate.html", context);
//
//        // Loop through each recipient and send the email
//        for (String recipientEmail : recipientEmails) {
//            try {
//                MimeMessage mimeMessage = javaMailSender().createMimeMessage();
//                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
//                helper.setTo(recipientEmail);
//                helper.setSubject(subject);
//                helper.setText(process, true);
//
//                javaMailSender().send(mimeMessage);
//            } catch (Exception e) {
//                e.printStackTrace();
//                allSent = false;
//                System.err.println("Failed to send email to: " + recipientEmail);
//            }
//        }
//
//        return allSent;
//    }
//
//
//
//
//}
