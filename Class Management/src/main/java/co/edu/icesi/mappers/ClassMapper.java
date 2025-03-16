package co.edu.icesi.mappers;

import co.edu.icesi.dto.ClassesDTO;
import co.edu.icesi.dto.ClassesResponseDTO;
import co.edu.icesi.dto.TrainerDTO;
import co.edu.icesi.persistence.model.Classes;
import co.edu.icesi.persistence.model.TrainerID;
import org.springframework.stereotype.Component;

@Component
public class ClassMapper {
    public Classes toClass(ClassesDTO classDTO) {
        return new Classes(
                classDTO.getId(),
                classDTO.getName(),
                classDTO.getSchedule(),
                classDTO.getMaxCapacity(),
                new TrainerID(classDTO.getTrainerId())
        );
    }

    public ClassesResponseDTO toDTO(Classes classes, TrainerDTO trainerDTO) {
        return ClassesResponseDTO.builder()
                .id(classes.getId())
                .name(classes.getName())
                .schedule(classes.getSchedule())
                .maxCapacity(classes.getMaxCapacity())
                .trainerDTO(trainerDTO)
                .build();
    }
}
