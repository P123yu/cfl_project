package com.cfl.cfl_project.service;

import com.cfl.cfl_project.model.RewardsAndRecognition;
import com.cfl.cfl_project.model.RewardsRecognitionDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface RewardsAndRecognitionService {

    RewardsAndRecognition create(String rewardsPersonName, String messagedPersonName, String message, MultipartFile file) throws IOException;

    RewardsAndRecognition getByRewardsPersonName(String rewardsPersonName);

    List<RewardsRecognitionDTO> getByRewardsAndRecognitionType(String rewardsRecognitionType);


    RewardsAndRecognition createBravo(String rewardsPersonName, String messagedPersonName, String message, MultipartFile file) throws IOException;

}
