package com.technicaltest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductInformation {
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private LocalDate expirationDate;

    @Override
    public String toString() {
        return exchangeCode + "-" + productGroupCode + "-" + symbol + "-" + expirationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

}
