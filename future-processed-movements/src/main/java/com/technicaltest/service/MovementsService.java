package com.technicaltest.service;

import com.technicaltest.dto.DailySummaryReport;

import java.io.ByteArrayInputStream;
import java.util.Set;

public interface MovementsService {
    Set<DailySummaryReport> getDailySummaryReport(String pathInputData);

    ByteArrayInputStream getDailySummaryReportDownload(String pathInputData, Set<DailySummaryReport> dailySummaryReport);
}
