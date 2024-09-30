package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.Cfl;
import com.cfl.cfl_project.model.Mentor;
import com.cfl.cfl_project.model.MentorResponse;
import com.cfl.cfl_project.repository.CflRepository;
import com.cfl.cfl_project.repository.MentorRepository;
import com.cfl.cfl_project.repository.RegisterRepository;
import com.cfl.cfl_project.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private CflRepository cflRepository;

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

        // set mentorId to cfl
        List<Cfl>cflList=new ArrayList<>();
        String []empIds=empId.split(",");
        for(String id :empIds){
            Cfl cfl=cflRepository.findByEmpId(Long.parseLong(id));
            cfl.setMentorId(mentorId);
            cflList.add(cfl);
        }
        cflRepository.saveAll(cflList);
        return mentorRepository.save(mentor);
    }



    @Override
    public MentorResponse getMentorByMentorMail(String mentorEmail) {
        Mentor mentor=mentorRepository.findByMentorEmail(mentorEmail);
        String empId=mentor.getEmpId();
        System.out.println(empId+"empId....");
        String []ListOfEmpId=empId.split(",");
        List<Cfl> listCfl=new ArrayList<>();
        for(String emp : ListOfEmpId){
            Cfl cfl=cflRepository.findByEmpId(Long.parseLong(emp));
            listCfl.add(cfl);
        }
        MentorResponse mentorResponse=new MentorResponse();
        mentorResponse.setMentor(mentor);
        mentorResponse.setCflList(listCfl);
        return mentorResponse;
    }

    @Override
    public List<String> getAll() {
        List<Mentor> mentorResponse = mentorRepository.findAll();
        List<String>mentorEmail=new ArrayList<>();
        for(Mentor i:mentorResponse){
            mentorEmail.add(i.getMentorEmail());
        }
        return mentorEmail;
    }


}
