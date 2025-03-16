package co.edu.icesi;

import co.edu.icesi.model.Miembro;
import co.edu.icesi.repository.MiembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  private MiembroRepository miembroRepository;

  @Override
  public void run(String... args) throws Exception {
    // Cargar miembros de ejemplo
    Miembro miembro1 = new Miembro();
    miembro1.setNombre("Juan Pérez");
    miembro1.setEmail("juan@email.com");
    miembro1.setFechaInscripcion(LocalDate.now());
    miembroRepository.save(miembro1);

    Miembro miembro2 = new Miembro();
    miembro2.setNombre("María López");
    miembro2.setEmail("maria@email.com");
    miembro2.setFechaInscripcion(LocalDate.now().minusDays(30));
    miembroRepository.save(miembro2);

    System.out.println("Miembros de ejemplo cargados exitosamente.");
  }
}
