package com.cfl.cfl_project.service.impl;

import com.cfl.cfl_project.model.LogBook;
import com.cfl.cfl_project.repository.LogBookRepository;
import com.cfl.cfl_project.service.LogBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
public class LogBookServiceImpl implements LogBookService {

    @Autowired
    private LogBookRepository logBookRepository;

    @Override
    public LogBook uploadLogBook(Long empId, MultipartFile certificateFile) throws IOException {
        LogBook logBook = new LogBook();
        logBook.setEmpId(empId);
        LocalDate date = LocalDate.now();
        logBook.setDate(date);
        LocalTime time= LocalTime.now();
        logBook.setTime(time);
        logBook.setLogBookFileName(certificateFile.getOriginalFilename());
        logBook.setLogBookFileData(certificateFile.getBytes());
        return logBookRepository.save(logBook);
    }

    @Override
    public List<LogBook> downloadLogBook(Long empId) {
        return logBookRepository.findByEmpId(empId);
    }
}
