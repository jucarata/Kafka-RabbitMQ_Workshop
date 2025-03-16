package co.edu.icesi;

import co.edu.icesi.persistence.model.Classes;
import co.edu.icesi.persistence.model.TrainerID;
import co.edu.icesi.persistence.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final ClassRepository classRepository;

    @Autowired
    public DataLoader(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        // Cargar clases de ejemplo
        Classes clase1 = new Classes();
        clase1.setName("Yoga Matutino");
        clase1.setSchedule(LocalDateTime.now().plusDays(1).withHour(8).withMinute(0));
        clase1.setMaxCapacity(20);
        clase1.setTrainerID(new TrainerID(1L));
        classRepository.save(clase1);

        // Cargar clases de ejemplo
        Classes clase2 = new Classes();
        clase2.setName("Futbol");
        clase2.setSchedule(LocalDateTime.now().plusDays(1).withHour(8).withMinute(0));
        clase2.setMaxCapacity(22);
        clase2.setTrainerID(new TrainerID(2L));
        classRepository.save(clase2);

        System.out.println("Datos de ejemplo cargados exitosamente.");
    }
}