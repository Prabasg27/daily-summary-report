package com.technicaltest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MovementDto {
    private String recordCode;
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String oppositePartyCode;
    private String productGroupCode;
    private String exchangeCode;
    private String symbol;
    private LocalDate expirationDate;
    private String currencyCode;
    private String movementCode;
    private String buySellCode;
    private String quantityLongSign;
    private Double quantityLong;
    private String quantityShortSign;
    private Double quantityShort;
    private Double exchBrokerFee;
    private String exchBrokerFeeDC;
    private String exchBrokerFeeCurCode;
    private Double clearingFee;
    private String clearingFeeDC;
    private String clearingFeeCurCode;
    private Double commission;
    private String commissionDC;
    private String commissionCurCode;
    private LocalDate transactionDate;
    private String futureReference;
    private String ticketNumber;
    private String externalNumber;
    private Double transactionPrice;
    private String traderInitials;
    private String oppositeTradeId;
    private String openCloseCode;
}
