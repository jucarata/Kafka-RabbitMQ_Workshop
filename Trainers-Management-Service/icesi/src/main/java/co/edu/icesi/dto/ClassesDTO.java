package co.edu.icesi.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class ClassesDTO implements Serializable {
    private Long id;
    private String name;
    private LocalDateTime schedule;
    private Integer maxCapacity;
    private Long trainerId;
}
