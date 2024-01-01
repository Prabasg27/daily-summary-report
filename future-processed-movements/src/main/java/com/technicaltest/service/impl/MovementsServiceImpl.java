package com.technicaltest.service.impl;

import com.technicaltest.dto.DailySummaryReport;
import com.technicaltest.dto.MovementDto;
import com.technicaltest.dto.MovementSpecDto;
import com.technicaltest.exception.InvalidMovementException;
import com.technicaltest.service.MovementSpecService;
import com.technicaltest.service.MovementsService;
import com.technicaltest.util.ReportUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.technicaltest.util.MovementsConstants.*;

@Slf4j
@Service
@AllArgsConstructor
public class MovementsServiceImpl implements MovementsService {
    private MovementSpecService movementSpecService;

    public Set<DailySummaryReport> getDailySummaryReport(String pathInputData) {
        return getSummaryReportSet(pathInputData);
    }

    @Override
    public ByteArrayInputStream getDailySummaryReportDownload(String pathInputData, Set<DailySummaryReport> dailySummaryReport){
        return ReportUtils.generateCSV(dailySummaryReport);
    }

    private Set<DailySummaryReport> getSummaryReportSet(String pathInputData) {
        List<MovementDto> movementList = getMovementList(pathInputData);

        Set<DailySummaryReport> dailySummaryReports = ReportUtils.generateMovementSummaryReport(movementList);
        log.info(dailySummaryReports.toString());

        return dailySummaryReports.stream().collect(Collectors.toUnmodifiableSet());
    }

    private List<MovementDto> getMovementList(String pathInputData) {
        Path filePath = Path.of(pathInputData);
        if (!Files.exists(filePath)) {
            throw new InvalidPathException(pathInputData, INVALID_SOURCE_PATH_MSG);
        }

        List<MovementSpecDto> movementSpecList = movementSpecService.getMovementSpecList();
        List<MovementDto> movementList = new ArrayList<>();

        try (Stream<String> lines = Files.lines(filePath, Charset.defaultCharset())) {
            lines.filter(line -> line.length() >= 176)
                    .forEach(extractMovements(movementSpecList, movementList));
        } catch (IOException ex) {
            throw new InvalidMovementException(INVALID_MOVEMENT_PROCESSING_MSG, ex);
        }
        return movementList;
    }


    private static Consumer<String> extractMovements(List<MovementSpecDto> movementSpecList, List<MovementDto> movementList) {
        return line -> {
            MovementDto movement = new MovementDto();
            int start = 0;
            for (Field field : movement.getClass().getDeclaredFields()) {
                MovementSpecDto movementSpecDto = movementSpecList.stream()
                        .filter(f -> field.getName().equals(f.getFieldMapper())).findFirst().orElse(new MovementSpecDto());

                if (movementSpecDto.getFieldLength() <= 0 || !StringUtils.hasText(movementSpecDto.getFieldName())) {
                    throw new InvalidMovementException(String.format(INVALID_MOVEMENT_SPEC_MSG, movementSpecDto));
                }

                try {
                    field.setAccessible(true);
                    String value = line.substring(start, start + movementSpecDto.getFieldLength()).trim();
                    switch (movementSpecDto.getFieldType()) {
                        case INTEGER:
                            field.set(movement, Integer.valueOf(value));
                            break;
                        case DECIMAL:
                            field.set(movement, Double.valueOf(value));
                            break;
                        case DATE:
                            field.set(movement, LocalDate.parse(value, DateTimeFormatter.ofPattern(DATE_FORMAT)));
                            break;
                        default:
                            field.set(movement, value);
                            break;
                    }
                    start += movementSpecDto.getFieldLength();
                } catch (IllegalAccessException ex) {
                    throw new InvalidMovementException(INVALID_MOVEMENT_VALUE_EXTRACTION_MSG, ex);
                }
            }
            movementList.add(movement);
        };
    }
}
