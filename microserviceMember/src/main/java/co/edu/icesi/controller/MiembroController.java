package co.edu.icesi.controller;

import co.edu.icesi.model.Miembro;
import co.edu.icesi.service.MiembroService; // Update with the correct package name
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
public class MiembroController {
  private final MiembroService miembroService;

  public MiembroController(MiembroService miembroService) {
    this.miembroService = miembroService;
  }

  // @PostMapping
  // public Miembro registrarMiembro(@RequestBody Miembro miembro) {
  // return miembroService.registrarMiembro(miembro);
  // }

  @PostMapping
  public Miembro registrarMiembro(@RequestBody Miembro miembro) {
    return miembroService.registrarMiembro(miembro);
  }

  /*
   * @GetMapping
   * public List<Miembro> obtenerTodosMiembros() {
   * return miembroService.obtenerTodosMiembros();
   * }
   */
  @GetMapping
  public List<Miembro> obtenerTodosMiembros() {
    return miembroService.obtenerTodosMiembros();
  }
}
