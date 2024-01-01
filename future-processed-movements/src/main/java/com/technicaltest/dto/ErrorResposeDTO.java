package com.technicaltest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ErrorResposeDTO {
    private String apiPath;
    private HttpStatus errorCode;
    private String errorDetails;
    private LocalDateTime occurredAt;
}
