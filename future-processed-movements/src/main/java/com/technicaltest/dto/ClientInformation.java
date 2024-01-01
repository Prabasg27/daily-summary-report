package com.technicaltest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ClientInformation {
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;

    @Override
    public String toString() {
        return clientType + "-" + clientNumber + "-" + accountNumber + "-" + subAccountNumber;
    }
}
