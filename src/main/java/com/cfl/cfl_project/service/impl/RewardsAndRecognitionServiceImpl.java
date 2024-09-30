package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.Cfl;
import com.cfl.cfl_project.model.RewardsAndRecognition;
import com.cfl.cfl_project.model.RewardsRecognitionDTO;
import com.cfl.cfl_project.repository.CflRepository;
import com.cfl.cfl_project.repository.RewardsAndRecognitionRepository;
import com.cfl.cfl_project.service.RewardsAndRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RewardsAndRecognitionServiceImpl implements RewardsAndRecognitionService {

    @Autowired
    private RewardsAndRecognitionRepository rewardsAndRecognitionRepository;


    @Override
    public RewardsAndRecognition create(String rewardsPersonName, String messagedPersonName, String message, MultipartFile file) throws IOException {
        RewardsAndRecognition rewardAndRecognition=new RewardsAndRecognition();
        rewardAndRecognition.setMessage(message);
        rewardAndRecognition.setRewardsPersonName(rewardsPersonName);
        rewardAndRecognition.setMessagedPersonName(messagedPersonName);
        rewardAndRecognition.setRewardImageName(file.getOriginalFilename());
        rewardAndRecognition.setRewardImage(file.getBytes());
        LocalDate date= LocalDate.now();
        rewardAndRecognition.setDate(date);
        rewardAndRecognition.setRewardRecognitionType("world-of-thanks");
        return rewardsAndRecognitionRepository.save(rewardAndRecognition);
    }





    @Override
    public RewardsAndRecognition getByRewardsPersonName(String rewardsPersonName) {
        return rewardsAndRecognitionRepository.findByRewardsPersonName(rewardsPersonName);
    }

    @Autowired
    private CflRepository cflRepository;

    @Override
    public List<RewardsRecognitionDTO> getByRewardsAndRecognitionType(String rewardsAndRecognitionType) {
        List<RewardsRecognitionDTO> rewardsRecognitionDTOList = new ArrayList<>();

        System.out.println(rewardsAndRecognitionType+"rewardsAndRecognitionType");

        List<RewardsAndRecognition> rewardsAndRecognitionsLists = rewardsAndRecognitionRepository.findByRewardRecognitionType(rewardsAndRecognitionType);
        System.out.println(String.valueOf(rewardsAndRecognitionsLists));

        for (RewardsAndRecognition rewardAndRecognition : rewardsAndRecognitionsLists) {
            RewardsRecognitionDTO rewardsRecognitionDTO = new RewardsRecognitionDTO();

            String[] nameParts = rewardAndRecognition.getRewardsPersonName().split(" ");
            if (nameParts.length < 2) {
                continue;
            }

            String firstName = nameParts[0];
            String lastName = nameParts[nameParts.length - 1];

            Cfl cflName = cflRepository.findByCflFirstNameAndCflLastName(firstName, lastName);

            if (cflName != null) {
                rewardsRecognitionDTO.setCfl(cflName);
                rewardsRecognitionDTO.setRewardsAndRecognition(rewardAndRecognition);
                rewardsRecognitionDTOList.add(rewardsRecognitionDTO);
            }
        }

        return rewardsRecognitionDTOList;
    }




    @Override
    public RewardsAndRecognition createBravo(String rewardsPersonName, String messagedPersonName, String message, MultipartFile file) throws IOException {
        RewardsAndRecognition rewardAndRecognition=new RewardsAndRecognition();
        rewardAndRecognition.setMessage(message);
        rewardAndRecognition.setRewardsPersonName(rewardsPersonName);
        rewardAndRecognition.setMessagedPersonName(messagedPersonName);
        rewardAndRecognition.setRewardImageName(file.getOriginalFilename());
        rewardAndRecognition.setRewardImage(file.getBytes());
        LocalDate date= LocalDate.now();
        rewardAndRecognition.setDate(date);
        rewardAndRecognition.setRewardRecognitionType("bravo");
        return rewardsAndRecognitionRepository.save(rewardAndRecognition);
    }

}
