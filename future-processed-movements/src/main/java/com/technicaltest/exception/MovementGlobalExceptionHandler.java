package com.technicaltest.exception;

import com.technicaltest.dto.ErrorResposeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.InvalidPathException;
import java.time.LocalDateTime;


@RestControllerAdvice
public class MovementGlobalExceptionHandler {

    @ExceptionHandler(CSVGeneratorException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResposeDTO> resourceNotFoundException(CSVGeneratorException ex, WebRequest request) {
        ErrorResposeDTO errorResposeDTO = ErrorResposeDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorDetails(ex.getMessage())
                .occurredAt(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResposeDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidPathException.class)
    @ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
    public ResponseEntity<ErrorResposeDTO> resourceNotFoundException(InvalidPathException ex, WebRequest request) {
        ErrorResposeDTO errorResposeDTO = ErrorResposeDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.FAILED_DEPENDENCY)
                .errorDetails(ex.getMessage())
                .occurredAt(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResposeDTO, HttpStatus.NOT_IMPLEMENTED);
    }
    @ExceptionHandler(InvalidMovementException.class)
    @ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<ErrorResposeDTO> resourceNotFoundException(InvalidMovementException ex, WebRequest request) {
        ErrorResposeDTO errorResposeDTO = ErrorResposeDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.NOT_IMPLEMENTED)
                .errorDetails(ex.getMessage())
                .occurredAt(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResposeDTO, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResposeDTO> globalExceptionHandler(Exception ex, WebRequest request) {
        ErrorResposeDTO errorResposeDTO = ErrorResposeDTO.builder()
                .apiPath(request.getDescription(false))
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorDetails(ex.getMessage())
                .occurredAt(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResposeDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
