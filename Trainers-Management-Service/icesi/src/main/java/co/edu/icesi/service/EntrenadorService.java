package co.edu.icesi.service;

import co.edu.icesi.model.Entrenador;
import co.edu.icesi.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }
    public Entrenador agregarEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Entrenador obtenerEntrenador(Long id) {
        return entrenadorRepository.findById(id).orElse(null);
    }
}
