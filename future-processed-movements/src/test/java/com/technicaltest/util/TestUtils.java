package com.technicaltest.util;

import com.technicaltest.dto.DailySummaryReport;
import com.technicaltest.dto.MovementSpecDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestUtils {
    private TestUtils() {
    }
    public static Set<DailySummaryReport> getDailySummaryReportMock() {
        DailySummaryReport report = new DailySummaryReport();
        report.setClient_Information("CL-1234-0002-0001");
        report.setProduct_Information("SGX-FU-NK-2010-09-10");
        report.setTotal_Transaction_Amount(-52.0);

        return new HashSet<>(Arrays.asList(report));
    }

    public static List<MovementSpecDto> getMovementSpecMock() {
        MovementSpecDto clientTypeSpec = MovementSpecDto.builder()
                .fieldName("CLIENT TYPE")
                .fieldLength(4)
                .fieldType("String")
                .fieldPosition(2)
                .fieldMapper("clientType").build();

        MovementSpecDto productGroupCodeSpec = MovementSpecDto.builder()
                .fieldName("PRODUCT GROUP CODE")
                .fieldLength(2)
                .fieldType("String")
                .fieldPosition(7)
                .fieldMapper("productGroupCodeSpec").build();

        return Arrays.asList(clientTypeSpec, productGroupCodeSpec);
    }

}
