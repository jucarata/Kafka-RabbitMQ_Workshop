package co.edu.icesi.controller;

import co.edu.icesi.model.Entrenador;
import co.edu.icesi.service.EntrenadorService;
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

    @PreAuthorize("hasAnyRole('USER_ROLE', 'ADMIN_ROLE')")
    @GetMapping
    public List<Entrenador> listarEntrenadores() {
        return entrenadorService.listarEntrenadores();
    }

    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PostMapping
    public Entrenador agregarEntrenador(@RequestBody Entrenador entrenador) {
        return entrenadorService.agregarEntrenador(entrenador);
    }

    @GetMapping("/{id}")
    public Entrenador obtenerEntrenador(@PathVariable Long id) {
        return entrenadorService.obtenerEntrenador(id);
    }
}
