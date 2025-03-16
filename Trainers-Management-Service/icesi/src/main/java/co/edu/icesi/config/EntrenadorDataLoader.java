package co.edu.icesi.config;

import co.edu.icesi.model.Entrenador;
import co.edu.icesi.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntrenadorDataLoader implements CommandLineRunner {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorDataLoader(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    @Override
    public void run(String... args) {
        if (entrenadorRepository.count() == 0) { // Evita duplicados
            Entrenador e1 = new Entrenador("Carlos López", "Crossfit");
            Entrenador e2 = new Entrenador("Ana Gómez", "Yoga");

            entrenadorRepository.save(e1);
            entrenadorRepository.save(e2);

            System.out.println("Datos de entrenadores insertados correctamente.");
        } else {
            System.out.println("Los datos ya existen, no se insertaron nuevamente.");
        }
    }
}
