package com.example.bi.controllers;

import com.example.bi.dto.DataInfoResponseDTO;
import com.example.bi.helper.ImportHelper;
import com.example.bi.service.DataService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DataController {

    private final DataService service;

    @PostMapping(value = "/api/file/upload")
    public ResponseEntity<Void> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String message = "";

        if (ImportHelper.hasExcelFormat(file)) {
            try {
                service.save(file);
                log.info("Uploaded the file successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                log.info("Could not upload the file: " + file.getOriginalFilename() + "!" + e.getMessage());
            }
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/api/export-csv")
    public void exportCSV(HttpServletResponse response) throws Exception {

        String filename = "data.csv";

        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        StatefulBeanToCsv<DataInfoResponseDTO> writer = new StatefulBeanToCsvBuilder<DataInfoResponseDTO>(response.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        writer.write(service.getAll());
    }

}
