package co.edu.icesi.service;

import co.edu.icesi.dto.ClassesDTO;
import co.edu.icesi.dto.ClassesResponseDTO;
import co.edu.icesi.exceptions.NoTrainerFoundException;

import java.util.List;

public interface ClassService {
    boolean scheduleClass(ClassesDTO classDTO) throws NoTrainerFoundException;
    List<ClassesResponseDTO> getClasses();
}