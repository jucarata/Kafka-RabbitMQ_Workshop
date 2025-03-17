package co.edu.icesi.controller;

import co.edu.icesi.model.Miembro;
import co.edu.icesi.service.MiembroService; // Update with the correct package name
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

  @PreAuthorize("hasAnyRole('USER_ROLE', 'ADMIN_ROLE')")
  @PostMapping
  public Miembro registrarMiembro(@RequestBody Miembro miembro) {
    return miembroService.registrarMiembro(miembro);
  }

  @PreAuthorize("hasRole('ADMIN_ROLE')")
  @GetMapping
  public List<Miembro> obtenerTodosMiembros() {
    return miembroService.obtenerTodosMiembros();
  }
}
