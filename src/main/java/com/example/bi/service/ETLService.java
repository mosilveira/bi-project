package com.example.bi.service;

import com.example.bi.entities.DataInfo;
import com.example.bi.helper.ExcelHelper;
import com.example.bi.repositories.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ETLService {

    private final DataRepository repository;

    public void save(MultipartFile file) {
        try {
            List<DataInfo> dataList = ExcelHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(dataList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<DataInfo> getAll() {
        return repository.findAll();
    }

}
