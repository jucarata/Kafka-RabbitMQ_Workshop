package co.edu.icesi.controller;

import co.edu.icesi.model.Equipo;
import co.edu.icesi.service.EquipoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @PreAuthorize("hasAnyRole('USER_ROLE', 'ADMIN_ROLE', 'TRAINER_ROLE')")
    @GetMapping
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoService.obtenerTodosEquipos();
    }

    // ✅ Agregar un nuevo equipo
    @PreAuthorize("hasRole('ADMIN_ROLE')")
    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoService.agregarEquipo(equipo);
    }
}
