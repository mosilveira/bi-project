package com.example.bi.controllers;

import com.example.bi.dto.DataInfoResponseDTO;
import com.example.bi.entities.DataInfo;
import com.example.bi.service.CSVService;
import com.example.bi.service.ETLService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class CSVController {

    private final CSVService service;

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
