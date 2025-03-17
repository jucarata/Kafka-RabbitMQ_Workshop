package co.edu.icesi.controller;

import co.edu.icesi.model.Equipo;
import co.edu.icesi.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@Tag(name = "Equipos", description = "API para la gestión de equipos deportivos")
@SecurityRequirement(name = "bearerAuth") // Para indicar que se requiere autenticación
public class EquipoController {
    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @Operation(
            summary = "Obtener todos los equipos",
            description = "Retorna la lista de todos los equipos registrados en el sistema. Requiere autenticación."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de equipos obtenida exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipo.class))),
            @ApiResponse(responseCode = "403", description = "Acceso denegado - Se requiere autenticación"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasAnyRole('USER_ROLE', 'ADMIN_ROLE', 'TRAINER_ROLE')")
    @GetMapping
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoService.obtenerTodosEquipos();
    }

    @Operation(
            summary = "Crear un nuevo equipo",
            description = "Permite registrar un nuevo equipo en el sistema. Solo disponible para administradores."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Equipo creado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Equipo.class))),
            @ApiResponse(responseCode = "403", description = "Acceso denegado - Se requiere rol de administrador"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoService.agregarEquipo(equipo);
    }
}
