package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.Mentor;
import com.cfl.cfl_project.model.MentorResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface MentorService {
    Boolean getByUserName(String userName);
    Boolean getByMentorEmail(String mentorEmail);
    Mentor createMentor(Long mentorId,String empId, String mentorName, String mentorEmail, String mentorDepartment, String mentorLocation, String mentorDesignation, MultipartFile mentorFile) throws IOException;
    MentorResponse getMentorByMentorMail(String mentorEmail);
    List<String> getAll();
}
