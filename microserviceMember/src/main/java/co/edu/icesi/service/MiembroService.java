package co.edu.icesi.service;

import co.edu.icesi.model.Miembro;
import co.edu.icesi.repository.MiembroRepository;
import co.edu.icesi.dto.NotificacionDTO; // Se usa la misma clase de notificación
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiembroService {
    private final MiembroRepository miembroRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public MiembroService(MiembroRepository miembroRepository) {
        this.miembroRepository = miembroRepository;
    }

    public Miembro registrarMiembro(Miembro miembro) {
        Miembro nuevoMiembro = miembroRepository.save(miembro);

        // ✅ Enviar notificación cuando se registra un nuevo miembro
        NotificacionDTO notificacion = new NotificacionDTO(
                "admin",
                "Se ha registrado un nuevo miembro: " + miembro.getNombre()
        );
        rabbitTemplate.convertAndSend(
                "notificacion.exchange",
                "notificacion.routingkey",
                notificacion
        );

        return nuevoMiembro;
    }

    public List<Miembro> obtenerTodosMiembros() {
        return miembroRepository.findAll();
    }
}
