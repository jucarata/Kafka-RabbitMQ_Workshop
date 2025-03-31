package co.edu.icesi.controller;

import co.edu.icesi.model.Entrenador;
import co.edu.icesi.service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/entrenadores")
    public class EntrenadorController {

        private final EntrenadorService entrenadorService;

        public EntrenadorController(EntrenadorService entrenadorService) {
            this.entrenadorService = entrenadorService;
        }

        @Operation(summary = "Listar entrenadores", description = "Obtiene una lista de todos los entrenadores disponibles.")
        @PreAuthorize("hasAnyRole('USER_ROLE', 'ADMIN_ROLE')")
        @GetMapping
        public List<Entrenador> listarEntrenadores() {
            return entrenadorService.listarEntrenadores();
        }

        @Operation(summary = "Agregar un nuevo entrenador", description = "Crea un nuevo entrenador en el sistema. Solo accesible para administradores.")
        @PreAuthorize("hasRole('ADMIN_ROLE')")
        @PostMapping
        public Entrenador agregarEntrenador(@RequestBody Entrenador entrenador) {
            return entrenadorService.agregarEntrenador(entrenador);
        }

        @Operation(summary = "Obtener un entrenador por ID", description = "Obtiene los datos de un entrenador específico.")
        @GetMapping("/{id}")
        public Entrenador obtenerEntrenador(@PathVariable Long id) {
            return entrenadorService.obtenerEntrenador(id);
        }
    }
