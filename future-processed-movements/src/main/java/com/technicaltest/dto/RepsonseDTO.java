package com.technicaltest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RepsonseDTO {
    private String statusCode;
    private String statusMsg;
    private Object data;
}
