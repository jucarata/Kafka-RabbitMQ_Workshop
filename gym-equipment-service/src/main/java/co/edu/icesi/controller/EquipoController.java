package co.edu.icesi.controller;

import co.edu.icesi.model.Equipo;
import co.edu.icesi.service.EquipoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {
    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // ✅ Obtener todos los equipos
    @GetMapping
    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoService.obtenerTodosEquipos();
    }

    // ✅ Agregar un nuevo equipo
    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoService.agregarEquipo(equipo);
    }
}
