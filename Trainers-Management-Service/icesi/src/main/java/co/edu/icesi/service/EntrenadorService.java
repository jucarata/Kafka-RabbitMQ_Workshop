package co.edu.icesi.service;

import co.edu.icesi.model.Entrenador;
import co.edu.icesi.repository.EntrenadorRepository;
import co.edu.icesi.dto.NotificacionDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate; // Para enviar notificaciones

    public EntrenadorService(EntrenadorRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public Entrenador agregarEntrenador(Entrenador entrenador) {
        Entrenador nuevoEntrenador = entrenadorRepository.save(entrenador);

        // Enviar notificación (ejemplo)
        NotificacionDTO notificacion = new NotificacionDTO(
                "admin",
                "Se ha agregado el entrenador: " + (nuevoEntrenador.getNombre() != null ? nuevoEntrenador.getNombre() : "")
        );
        rabbitTemplate.convertAndSend(
                "notificacion.exchange",
                "notificacion.routingkey",
                notificacion
        );

        return nuevoEntrenador;
    }

    public Entrenador obtenerEntrenador(Long id) {
        return entrenadorRepository.findById(id).orElse(null);
    }
}
