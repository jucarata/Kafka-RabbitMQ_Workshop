package co.edu.icesi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class ClassesDTO {
    private Long id;
    private String name;
    private LocalDateTime schedule;
    private Integer maxCapacity;
    private Long trainerId;
}
