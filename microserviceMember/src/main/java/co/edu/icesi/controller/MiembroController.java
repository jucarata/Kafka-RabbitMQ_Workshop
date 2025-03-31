package co.edu.icesi.controller;

import co.edu.icesi.model.Miembro;
import co.edu.icesi.service.MiembroService; // Update with the correct package name
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {
  private final MiembroService miembroService;

  public MiembroController(MiembroService miembroService) {
    this.miembroService = miembroService;
  }

  @Operation(summary = "Registrar un nuevo miembro", description = "Registra un nuevo miembro en el sistema")
  @PreAuthorize("hasAnyRole('USER_ROLE', 'ADMIN_ROLE')")
  @PostMapping
  public Miembro registrarMiembro(@RequestBody Miembro miembro) {
    return miembroService.registrarMiembro(miembro);
  }

  @Operation(summary = "Obtener todos los miembros", description = "Obtiene una lista de todos los miembros registrados en el sistema")
  @PreAuthorize("hasRole('ADMIN_ROLE')")
  @GetMapping
  public List<Miembro> obtenerTodosMiembros() {
    return miembroService.obtenerTodosMiembros();
  }
}
