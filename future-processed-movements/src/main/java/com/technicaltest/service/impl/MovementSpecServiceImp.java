package com.technicaltest.service.impl;

import com.technicaltest.dto.MovementSpecDto;
import com.technicaltest.entity.MovementSpec;
import com.technicaltest.repo.MovementSpecRepository;
import com.technicaltest.service.MovementSpecService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MovementSpecServiceImp implements MovementSpecService {
    private MovementSpecRepository movementSpecRepository;

    public List<MovementSpecDto> getMovementSpecList() {
        List<MovementSpec> movementSpecList = movementSpecRepository.findByOrderByFieldPositionAsc();
        return movementSpecList.stream().map(this::mapToDto).toList();
    }

    private MovementSpecDto mapToDto(MovementSpec movementSpec) {
        return MovementSpecDto.builder()
                .fieldName(movementSpec.getFieldName())
                .fieldType(movementSpec.getFieldType())
                .fieldLength(movementSpec.getFieldLength())
                .fieldPosition(movementSpec.getFieldPosition())
                .fieldMapper(movementSpec.getFieldMapper())
                .build();
    }

}
