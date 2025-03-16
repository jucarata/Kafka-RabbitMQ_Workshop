package co.edu.icesi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ClassesResponseDTO {
    private Long id;
    private String name;
    private LocalDateTime schedule;
    private Integer maxCapacity;
    private TrainerDTO trainerDTO;
}