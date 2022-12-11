package com.example.bi.controllers;

import com.example.bi.dto.ResponseMessage;
import com.example.bi.entities.DataInfo;
import com.example.bi.helper.ExcelHelper;
import com.example.bi.service.ETLService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ETLController {

    private final ETLService etlService;

    @PostMapping(value = "/api/file/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";

        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                etlService.save(file);
                log.info("Uploaded the file successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                log.info("Could not upload the file: " + file.getOriginalFilename() + "!" + e.getMessage());
            }
        }

        log.info("Please upload an excel file!");
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "api/file")
    public List<DataInfo> getAll() {
        return etlService.getAll();
    }

}
