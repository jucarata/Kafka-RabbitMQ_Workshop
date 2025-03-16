package co.edu.icesi.service;

import co.edu.icesi.model.Equipo;
import co.edu.icesi.repository.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // ✅ Agregar un equipo
    public Equipo agregarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    // ✅ Obtener todos los equipos
    public List<Equipo> obtenerTodosEquipos() {
        return equipoRepository.findAll();
    }
}

