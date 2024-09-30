package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.Video;
import com.cfl.cfl_project.repository.VideoRepository;
import com.cfl.cfl_project.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video create(Video video){
        return videoRepository.save(video);
    }

    @Override
    public List<Video> getByYear(String year) {
        return videoRepository.findByYear(year);
    }
}
