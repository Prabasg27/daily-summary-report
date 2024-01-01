package com.technicaltest.controller;

import com.technicaltest.dto.*;
import com.technicaltest.service.MovementsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1")
public class DailySummaryReportController {
    private MovementsService movementsService;

    @Autowired
    public DailySummaryReportController(MovementsService movementsService) {
        this.movementsService = movementsService;
    }

    @Value("${path.input.data}")
    private String pathInputData;

    @GetMapping(path = "/json", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Set<DailySummaryReport>> getDailySummaryReport() {
        Set<DailySummaryReport> dailySummaryReport = movementsService.getDailySummaryReport(pathInputData);
        return ResponseEntity.ok(dailySummaryReport);
    }

    @GetMapping(path = "/csv")
    public ResponseEntity<Resource> getDailySummaryReportDownload() {
        Set<DailySummaryReport> dailySummaryReport = movementsService.getDailySummaryReport(pathInputData);
        InputStreamResource summaryReportData = new InputStreamResource(
                movementsService.getDailySummaryReportDownload(pathInputData, dailySummaryReport));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(summaryReportData);
    }
}
