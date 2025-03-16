package co.edu.icesi.service;

import co.edu.icesi.model.Miembro;
import co.edu.icesi.repository.MiembroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroService {
    private final MiembroRepository miembroRepository;

    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    public Miembro registrarMiembro(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    public List<Miembro> obtenerTodosMiembros() {
        return miembroRepository.findAll();
    }
}
