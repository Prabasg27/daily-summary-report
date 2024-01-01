package com.technicaltest.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class DailySummaryReport {
    private String client_Information;
    private String product_Information;
    private Double Total_Transaction_Amount;

    public DailySummaryReport(String clientInformation, String productInformation){
        this.client_Information = clientInformation;
        this.product_Information = productInformation;
    }
}
