package co.edu.icesi.controller;

import co.edu.icesi.dto.ClassesDTO;
import co.edu.icesi.dto.ClassesResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassController {
    ResponseEntity<String> scheduleClass(ClassesDTO classDTO);
    ResponseEntity<List<ClassesResponseDTO>> getClasses();
}