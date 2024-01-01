package com.technicaltest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import static com.technicaltest.util.MovementsConstants.*;

@Entity
@Table(name = "movement_spec")
@Getter @Setter @ToString
public class MovementSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message= FIELD_NAME_MUST_NOT_BE_BLANK)
    @Column(name = FIELD_NAME)
    private String fieldName;

    @NotBlank(message= FIELD_LENGTH_MUST_NOT_BE_BLANK)
    @Positive
    @Column(name = FIELD_LENGTH)
    private Integer fieldLength;

    @NotBlank(message= FIELD_POSITION_MUST_NOT_BE_BLANK)
    @Positive
    @Column(name = FIELD_POSITION)
    private Integer fieldPosition;

    @NotBlank(message= FIELD_TYPE_MUST_NOT_BE_BLANK)
    @Size(min=3, message= FIELD_TYPE_MUST_BE_AT_LEAST_3_CHARACTERS_LONG)
    @Column(name = FIELD_TYPE)
    private String fieldType;

    @NotBlank(message= FIELD_MAPPER_MUST_NOT_BE_BLANK)
    @Column(name = FIELD_MAPPER)
    private String fieldMapper;
}
