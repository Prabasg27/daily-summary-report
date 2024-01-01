package com.technicaltest.service.impl;

import com.technicaltest.exception.InvalidMovementException;
import com.technicaltest.service.MovementSpecService;
import com.technicaltest.service.MovementsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.nio.file.InvalidPathException;

import static com.technicaltest.util.MovementsConstants.INVALID_MOVEMENT_PROCESSING_MSG;
import static com.technicaltest.util.MovementsConstants.INVALID_SOURCE_PATH_MSG;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MovementsServiceImplTest {

    public static final String SRC_INPUT_TXT = "src/input.txt";

    @Spy
    private MovementsService movementsService;
    @Mock
    private MovementSpecService movementSpecService;
    @Test
    public void getDailySummaryReportInvalidPathTest() {

        when(movementsService.getDailySummaryReport(anyString())).thenThrow(new InvalidPathException("", INVALID_SOURCE_PATH_MSG));
        Exception exception = assertThrows(InvalidPathException.class,
                () -> movementsService.getDailySummaryReport(""));

        assertTrue(exception.getMessage().contains("Invalid source file path"));
    }

    @Test
    public void getDailySummaryReportInvalidMovementExceptionTest() {

        when(movementsService.getDailySummaryReport(anyString())).thenThrow(
                new InvalidMovementException(INVALID_MOVEMENT_PROCESSING_MSG));

        Exception exception = assertThrows(InvalidMovementException.class,
                () -> movementsService.getDailySummaryReport(SRC_INPUT_TXT));

        assertTrue(exception.getMessage().contains("Error while processing movement data : "));
    }
}