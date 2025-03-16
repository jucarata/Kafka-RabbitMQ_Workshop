package co.edu.icesi.controller;

import co.edu.icesi.dto.ClassesDTO;
import co.edu.icesi.dto.ClassesResponseDTO;
import co.edu.icesi.exceptions.NoTrainerFoundException;
import co.edu.icesi.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clases")
public class ClassControllerImpl implements ClassController {

    private final ClassService classService;

    @Autowired
    public ClassControllerImpl(ClassService classService) {
        this.classService = classService;
    }

    @Override
    @Operation(summary = "Programa una nueva clase", description = "Crea una nueva clase si hay un entrenador disponible.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Clase creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "No se pudo programar la clase, verifique los datos"),
            @ApiResponse(responseCode = "401", description = "No autorizado, se requiere token válido"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado, se requieren permisos adecuados")
    })
    @PreAuthorize("hasAnyRole('USER_ROLE', 'ADMIN_ROLE')")
    @PostMapping
    public ResponseEntity<String> scheduleClass(@RequestBody ClassesDTO classDTO) {
        boolean wasCreated = false;
        try {
            wasCreated = classService.scheduleClass(classDTO);
        } catch (NoTrainerFoundException e) {
            return ResponseEntity.badRequest().body("No hay ningun entrenador que coincida con el id asociado");
        }
        return wasCreated ? ResponseEntity.status(201).build()
                : ResponseEntity.badRequest().body("No se pudo programar la nueva clase, verifique que los datos sean correctos");
    }

    @Override
    @Operation(summary = "Obtiene todas las clases", description = "Retorna una lista de todas las clases registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clases obtenida exitosamente"),
            @ApiResponse(responseCode = "401", description = "No autorizado, se requiere token válido"),
            @ApiResponse(responseCode = "403", description = "Acceso denegado, solo administradores pueden ver las clases")
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @GetMapping
    public ResponseEntity<List<ClassesResponseDTO>> getClasses() {
        List<ClassesResponseDTO> classes = classService.getClasses();
        return ResponseEntity.ok(classes);
    }
}