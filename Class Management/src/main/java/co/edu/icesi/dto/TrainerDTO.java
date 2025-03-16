package co.edu.icesi.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrainerDTO {
    private Long id;
    private String nombre;
}