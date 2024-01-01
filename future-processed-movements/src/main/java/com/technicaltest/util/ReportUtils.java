package com.technicaltest.util;

import com.technicaltest.dto.DailySummaryReport;
import com.technicaltest.dto.MovementDto;
import com.technicaltest.exception.CSVGeneratorException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.technicaltest.util.MovementsConstants.SEPERATOR;

public class ReportUtils {

    private ReportUtils() {
    }
    public static Set<DailySummaryReport> generateMovementSummaryReport(List<MovementDto> movementList) {

        Map<DailySummaryReport, Double> mapSummaryReport = movementList.stream().collect(Collectors.groupingBy(r ->
                        new DailySummaryReport(r.getClientType() + SEPERATOR + r.getClientNumber() + SEPERATOR
                                + r.getAccountNumber() + SEPERATOR + r.getSubAccountNumber()
                                , r.getExchangeCode() + SEPERATOR + r.getProductGroupCode() + SEPERATOR
                                + r.getSymbol() + SEPERATOR + r.getExpirationDate())
                , Collectors.summingDouble(r -> (r.getQuantityLong() - r.getQuantityShort()))));


        for (Map.Entry<DailySummaryReport, Double> map: mapSummaryReport.entrySet()) {
            map.getKey().setTotal_Transaction_Amount(map.getValue());
        }

        Set<DailySummaryReport> dailySummaryReports = mapSummaryReport.keySet();

        return dailySummaryReports;
    }

    public static ByteArrayInputStream generateCSV(Set<DailySummaryReport> summaryReports) {
        ByteArrayInputStream inputStream = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(outputStream), CSVFormat.DEFAULT);) {

            csvPrinter.printRecord("Client_Information", "Product_Information", "Total_Transaction_Amount");

            for (DailySummaryReport report: summaryReports) {
                csvPrinter.printRecord(
                        report.getClient_Information(),
                        report.getProduct_Information(),
                        String.valueOf(report.getTotal_Transaction_Amount())
                );
            }
            csvPrinter.flush();
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException ex) {
            throw new CSVGeneratorException("Error while generating CSV data : ", ex);
        }
        return inputStream;
    }
}
