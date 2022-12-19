package com.example.bi.service;

import com.example.bi.dto.DataInfoResponseDTO;
import com.example.bi.entities.DataInfo;
import com.example.bi.helper.ImportHelper;
import com.example.bi.repositories.DataRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {

    private final DataRepository repository;

    private final ModelMapper modelMapper;

    public void save(MultipartFile file) {
        try {
            List<DataInfo> dataList = ImportHelper.excelToTutorials(file.getInputStream());
            repository.saveAll(dataList);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<DataInfoResponseDTO> getAll() {
        List<DataInfo> dataList = repository.findAll();
        ArrayList<DataInfoResponseDTO> responseList = new ArrayList<>();

        dataList.forEach(data -> {
            DataInfoResponseDTO dataDTO = modelMapper.map(data, DataInfoResponseDTO.class);

            dataDTO.setUniqueCareInfo(data.getCareInfo() == 0 ? 0 : 1);
            dataDTO.setUniquePa(data.getPa() == 0 ? 0 : 1);
            dataDTO.setPaOrCareInfo(data.getCareInfo() != 0 || data.getPa() != 0 ? 1 : 0);

            responseList.add(dataDTO);
        });

        return responseList;
    }

}
