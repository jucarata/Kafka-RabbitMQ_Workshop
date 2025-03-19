package co.edu.icesi.service;

import co.edu.icesi.model.Equipo;
import co.edu.icesi.repository.EquipoRepository;
import co.edu.icesi.dto.NotificacionDTO;
import co.edu.icesi.config.RabbitMQConfig; // Ajusta el package si es diferente
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public Equipo agregarEquipo(Equipo equipo) {
        // Guardamos el equipo en la BD
        Equipo equipoGuardado = equipoRepository.save(equipo);

        // Construimos el mensaje de notificación
        NotificacionDTO notificacion = new NotificacionDTO(
                "admin",
                "Se ha agregado el equipo: " + equipo.getNombre()
        );

        // Enviamos al exchange y routing key configurados
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.NOTIFICATION_ROUTING_KEY,
                notificacion
        );

        return equipoGuardado;
    }

    public List<Equipo> obtenerTodosEquipos() {
        return equipoRepository.findAll();
    }
}
