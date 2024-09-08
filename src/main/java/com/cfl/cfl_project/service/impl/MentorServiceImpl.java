package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.Mentor;
import com.cfl.cfl_project.repository.MentorRepository;
import com.cfl.cfl_project.repository.RegisterRepository;
import com.cfl.cfl_project.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MentorServiceImpl implements MentorService {

    @Autowired
    private RegisterRepository registerRepository;

    @Override
    public Boolean getByUserName(String userName) {
        return registerRepository.existsByUserName(userName);
    }


    @Autowired
    private MentorRepository mentorRepository;

    @Override
    public Boolean getByMentorEmail(String mentorEmail) {
       return  mentorRepository.existsByMentorEmail(mentorEmail);
    }

    @Override
    public Mentor createMentor(Long mentorId, String empId, String mentorName, String mentorEmail, String mentorDepartment, String mentorLocation, String mentorDesignation, MultipartFile mentorFile) throws IOException {
        Mentor mentor =new Mentor();
        mentor.setMentorId(mentorId);
        mentor.setEmpId(empId);
        mentor.setMentorName(mentorName);
        mentor.setMentorEmail(mentorEmail);
        mentor.setMentorDepartment(mentorDepartment);
        mentor.setMentorLocation(mentorLocation);
        mentor.setMentorDesignation(mentorDesignation);
        mentor.setMentorFileName(mentorFile.getOriginalFilename());
        mentor.setMentorFileData(mentorFile.getBytes());
        return mentorRepository.save(mentor);
    }


}
